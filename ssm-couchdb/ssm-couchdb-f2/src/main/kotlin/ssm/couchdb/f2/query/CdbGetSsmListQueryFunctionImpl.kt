package ssm.couchdb.f2.query

import f2.function.spring.adapter.f2Function
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import ssm.coucbdb.dsl.query.CdbGetSsmListQueryFunction
import ssm.coucbdb.dsl.query.CdbGetSsmListQueryResult
import ssm.couchdb.client.SsmCouchDbClient
import ssm.dsl.DocType

@Configuration
class CdbGetSsmListQueryFunctionImpl(
    private val ssmCouchDbClient: SsmCouchDbClient
) {

    @Bean
    fun cdbGetSsmListQueryFunction(): CdbGetSsmListQueryFunction = f2Function { cmd ->
        ssmCouchDbClient.fetchAllByDocType(cmd.dbName, DocType.Ssm)
            .let(::CdbGetSsmListQueryResult)
    }
}