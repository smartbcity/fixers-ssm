package ssm.couchdb.f2.commons

import f2.dsl.function.F2Function
import f2.function.spring.adapter.f2Function
import org.springframework.context.annotation.Configuration
import ssm.couchdb.autoconfiguration.SsmCouchdbProperties
import ssm.couchdb.client.SsmCouchDbClient
import ssm.couchdb.client.builder.SsmCouchDbBasicAuth
import ssm.couchdb.dsl.CdbQuery

typealias SsmCouchdbConfig = Map<String, SsmCouchdbProperties>

@Configuration
class CdbF2Function(
    private val ssmCouchdbAutoconfiguration: SsmCouchdbConfig
) {

    fun ssmCouchDbClients(dbName: String): SsmCouchDbClient? {
        return ssmCouchdbAutoconfiguration[dbName]?.let { config ->
            SsmCouchDbClient.builder()
                .withUrl(config.url)
                .withName(config.serviceName)
                .withAuth(
                    SsmCouchDbBasicAuth(
                        username = config.username,
                        password = config.password,
                    )
                ).build()
        }
    }

    fun <T: CdbQuery, R> function(fnc: suspend (T, SsmCouchDbClient) -> R): F2Function<T, R> = f2Function { cmd ->
        val cdbClient = ssmCouchDbClients(cmd.dbConfig)
            ?: throw IllegalArgumentException("Couchdb config [${cmd.dbConfig}] not found")

        fnc(cmd, cdbClient)
    }
}

