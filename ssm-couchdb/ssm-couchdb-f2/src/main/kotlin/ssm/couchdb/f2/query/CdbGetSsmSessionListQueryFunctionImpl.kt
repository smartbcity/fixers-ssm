package ssm.couchdb.f2.query

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import ssm.couchdb.dsl.query.CdbGetSsmSessionListQueryFunction
import ssm.couchdb.dsl.query.CdbGetSsmSessionListQueryResult
import ssm.couchdb.f2.commons.cdbF2Function
import ssm.dsl.DocType
import ssm.dsl.SsmSessionState

@Configuration
class CdbGetSsmSessionListQueryFunctionImpl {

    @Bean
    fun cdbGetSsmSessionListQueryFunction(): CdbGetSsmSessionListQueryFunction = cdbF2Function { cmd, cdbClient ->
        val filters = cmd.ssm?.let { ssm ->
            mapOf(SsmSessionState::ssm.name to ssm)
        } ?: emptyMap()

        cdbClient.fetchAllByDocType(cmd.dbName, DocType.State, filters)
            .toTypedArray()
            .let(::CdbGetSsmSessionListQueryResult)
    }
}