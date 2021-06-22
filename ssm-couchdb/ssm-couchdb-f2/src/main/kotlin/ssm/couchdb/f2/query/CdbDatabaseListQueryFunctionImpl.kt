package ssm.couchdb.f2.query

import kotlinx.coroutines.flow.*
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import ssm.couchdb.dsl.Database
import ssm.couchdb.dsl.query.*
import ssm.couchdb.f2.commons.CdbF2Function

@Configuration
class CdbDatabaseListQueryFunctionImpl(
	private val cbdf2: CdbF2Function
) {

	@Bean
	fun cdbDatabaseListQueryFunction(): CdbDatabaseListQueryFunction = cbdf2.function { _, cdbClient ->
		cdbClient.cloudant.allDbs.execute()
			.result
			.asFlow()
			.toDatabases()
			.let(::CdbDatabaseListQueryResult)
	}

	private suspend fun Flow<String>.toDatabases(): List<Database> =
		map(::Database).toList()

}
