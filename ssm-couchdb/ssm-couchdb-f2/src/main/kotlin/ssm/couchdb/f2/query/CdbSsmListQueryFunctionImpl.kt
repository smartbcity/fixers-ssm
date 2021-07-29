package ssm.couchdb.f2.query

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import ssm.couchdb.dsl.DocType
import ssm.couchdb.dsl.query.CdbSsmListQueryFunction
import ssm.couchdb.dsl.query.CdbSsmListQueryResult
import ssm.couchdb.f2.commons.CdbF2Function

@Configuration
class CdbSsmListQueryFunctionImpl(
    private val cbdf2: CdbF2Function
) {

    @Bean
    fun cdbSsmListQueryFunction(): CdbSsmListQueryFunction = cbdf2.function { cmd, cdbClient ->
        cdbClient.fetchAllByDocType(cmd.dbName, DocType.Ssm)
            .let(::CdbSsmListQueryResult)
    }
}