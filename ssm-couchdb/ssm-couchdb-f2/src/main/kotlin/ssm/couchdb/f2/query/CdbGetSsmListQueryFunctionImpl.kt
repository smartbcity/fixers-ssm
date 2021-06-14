package ssm.couchdb.f2.query

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import ssm.couchdb.dsl.query.CdbGetSsmListQueryFunction
import ssm.couchdb.dsl.query.CdbGetSsmListQueryResult
import ssm.couchdb.f2.commons.cdbF2Function
import ssm.dsl.DocType

@Configuration
class CdbGetSsmListQueryFunctionImpl {

    @Bean
    fun cdbGetSsmListQueryFunction(): CdbGetSsmListQueryFunction = cdbF2Function { cmd, cdbClient ->
        cdbClient.fetchAllByDocType(cmd.dbName, DocType.Ssm)
            .let(::CdbGetSsmListQueryResult)
    }
}