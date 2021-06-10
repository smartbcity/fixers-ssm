package ssm.couchdb.f2.query

import f2.function.spring.adapter.f2Function
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import ssm.coucbdb.dsl.query.CdbGetSsmSessionListQueryFunction
import ssm.coucbdb.dsl.query.CdbGetSsmSessionListQueryResult
import ssm.couchdb.client.SsmCouchDbClient
import ssm.dsl.DocType
import ssm.dsl.SsmSessionState

@Configuration
class CdbGetSsmSessionListQueryFunctionImpl(
    private val ssmCouchDbClient: SsmCouchDbClient
) {

    @Bean
    fun cdbGetSsmSessionListQueryFunction(): CdbGetSsmSessionListQueryFunction = f2Function { cmd ->
        val filters = cmd.ssm?.let { ssm ->
            mapOf(SsmSessionState::ssm.name to ssm)
        } ?: emptyMap()

        ssmCouchDbClient.fetchAllByDocType(cmd.dbName, DocType.State, filters)
            .toTypedArray()
            .let(::CdbGetSsmSessionListQueryResult)
    }
}