package ssm.couchdb.f2.query

import f2.dsl.cqrs.page.Page
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.toList
import ssm.couchdb.client.SsmCouchdbClient
import ssm.couchdb.dsl.model.Database
import ssm.couchdb.dsl.query.CouchdbDatabaseListQueryDTO
import ssm.couchdb.dsl.query.CouchdbDatabaseListQueryFunction
import ssm.couchdb.dsl.query.CouchdbDatabaseListQueryResult
import ssm.couchdb.dsl.query.CouchdbDatabaseListQueryResultDTO

class CouchdbDatabaseListQueryFunctionImpl(
	private val couchdbClient: SsmCouchdbClient,
) : CouchdbDatabaseListQueryFunction {

	override suspend fun invoke(msg: Flow<CouchdbDatabaseListQueryDTO>): Flow<CouchdbDatabaseListQueryResultDTO> = msg.map { payload ->
		val total = 0
		val filter = listOfNotNull(payload.channelId, "_", payload.chaincodeId).joinToString()

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
					pagination = payload.pagination
				)
			}
	}

	private suspend fun Flow<String>.toDatabases(): List<Database> =
		map(::Database).toList()
}
