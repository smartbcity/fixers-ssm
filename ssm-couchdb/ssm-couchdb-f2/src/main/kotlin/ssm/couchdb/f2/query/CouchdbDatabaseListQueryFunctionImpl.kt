package ssm.couchdb.f2.query

import f2.dsl.cqrs.page.Page
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.toList
import ssm.couchdb.dsl.config.CouchdbConfig
import ssm.couchdb.dsl.model.Database
import ssm.couchdb.dsl.query.CouchdbDatabaseListQueryFunction
import ssm.couchdb.dsl.query.CouchdbDatabaseListQueryResult
import ssm.couchdb.f2.commons.CouchdbF2Function

class CouchdbDatabaseListQueryFunctionImpl(
	private val config: CouchdbConfig,
) {

	fun couchdbDatabaseListQueryFunction(): CouchdbDatabaseListQueryFunction =
		CouchdbF2Function.function(config) { query, couchdbClient ->
			val total = 0
			query.chaincodeId
			val filter = listOfNotNull(query.channelId, "_", query.chaincodeId).joinToString()

			couchdbClient.cloudant.allDbs.execute()
				.result
				.asFlow()
				.filter { it.contains(filter) }
				.toDatabases()
				.let { list ->

					CouchdbDatabaseListQueryResult(
						page = Page(
							total = total,
							list = list,
						),
						pagination = query.pagination
					)
				}
		}

	private suspend fun Flow<String>.toDatabases(): List<Database> =
		map(::Database).toList()
}
