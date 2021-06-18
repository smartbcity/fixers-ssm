package ssm.couchdb.f2.query

import f2.dsl.function.F2Function
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.onEach
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import ssm.couchdb.dsl.query.CdbGetDatabasesQuery
import ssm.couchdb.f2.commons.cdbF2Function

@Configuration
class CdbGetDatabasesFunctionImpl {

	@Bean
	fun cdbGetDatabasesFunction(): F2Function<CdbGetDatabasesQuery, Flow<String>> = cdbF2Function { _, cdbClient ->
		cdbClient.cloudant.allDbs.execute().result.asFlow().onEach { db ->
			println(db)
		}
	}
}
