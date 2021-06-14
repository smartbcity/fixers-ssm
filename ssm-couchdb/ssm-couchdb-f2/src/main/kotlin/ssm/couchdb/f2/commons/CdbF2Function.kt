package ssm.couchdb.f2.commons

import f2.dsl.function.F2Function
import f2.function.spring.adapter.f2Function
import org.springframework.context.annotation.Configuration
import ssm.couchdb.autoconfiguration.SsmCouchdbAutoconfiguration
import ssm.couchdb.client.SsmCouchDbClient
import ssm.couchdb.dsl.CdbCommand

private lateinit var config: SsmCouchdbAutoconfiguration

@Configuration
private class CdbF2Initializer(
    private val ssmCouchdbAutoconfiguration: SsmCouchdbAutoconfiguration
) {
    init {
        config = this.ssmCouchdbAutoconfiguration
    }
}

fun <T: CdbCommand, R> cdbF2Function(fnc: suspend (T, SsmCouchDbClient) -> R): F2Function<T, R> = f2Function { cmd ->
    println(config.ssmCouchDbClients())
    val cdbClient = config.ssmCouchDbClients()[cmd.dbConfig]
        ?: throw IllegalArgumentException("Couchdb config [${cmd.dbConfig}] not found")

    fnc(cmd, cdbClient)
}