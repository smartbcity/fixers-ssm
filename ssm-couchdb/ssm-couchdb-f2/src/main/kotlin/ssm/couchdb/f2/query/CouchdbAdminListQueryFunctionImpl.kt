package ssm.couchdb.f2.query

import f2.dsl.cqrs.page.Page
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.toList
import ssm.chaincode.dsl.model.uri.burstChaincode
import ssm.couchdb.dsl.config.SsmCouchdbConfig
import ssm.couchdb.dsl.model.Database
import ssm.couchdb.dsl.model.DocType
import ssm.couchdb.dsl.query.CouchdbAdminListQueryFunction
import ssm.couchdb.dsl.query.CouchdbAdminListQueryResult
import ssm.couchdb.dsl.query.CouchdbAdminListQueryResultDTO
import ssm.couchdb.dsl.query.CouchdbDatabaseListQueryFunction
import ssm.couchdb.dsl.query.CouchdbDatabaseListQueryResult
import ssm.couchdb.f2.commons.CouchdbF2Function
import ssm.couchdb.f2.commons.chainCodeDbName

class CouchdbAdminListQueryFunctionImpl(
	private val config: SsmCouchdbConfig,
) {

	fun couchdbAdminListQueryFunction(): CouchdbAdminListQueryFunction =
		CouchdbF2Function.function(config) { query, couchdbClient ->
			val chaincode = query.chaincodeUri.burstChaincode()!!
			val dbName = chainCodeDbName(chaincode.channelId, chaincode.chaincodeId)
			couchdbClient.fetchAllByDocType(dbName, DocType.Admin)
				.let {
					CouchdbAdminListQueryResult(it)
				}
		}

	private suspend fun Flow<String>.toDatabases(): List<Database> =
		map(::Database).toList()
}
