package ssm.couchdb.f2.query

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.toList
import ssm.chaincode.dsl.model.uri.burstChaincode
import ssm.couchdb.dsl.config.SsmCouchdbConfig
import ssm.couchdb.dsl.model.Database
import ssm.couchdb.dsl.model.DocType
import ssm.couchdb.dsl.query.CouchdbUserListQueryFunction
import ssm.couchdb.dsl.query.CouchdbUserListQueryResult
import ssm.couchdb.f2.commons.CouchdbF2Function
import ssm.couchdb.f2.commons.chainCodeDbName

class CouchdbUserListQueryFunctionImpl(
	private val config: SsmCouchdbConfig,
) {

	fun couchdbUserListQueryFunction(): CouchdbUserListQueryFunction =
		CouchdbF2Function.function(config) { query, couchdbClient ->
			val chaincode = query.chaincodeUri.burstChaincode()!!
			val dbName = chainCodeDbName(chaincode.channelId, chaincode.chaincodeId)
			couchdbClient.fetchAllByDocType(dbName, DocType.User)
				.let {
					CouchdbUserListQueryResult(it)
				}
		}

	private suspend fun Flow<String>.toDatabases(): List<Database> =
		map(::Database).toList()
}
