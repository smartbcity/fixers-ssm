package ssm.couchdb.f2.query

import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flatMapMerge
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.toList
import ssm.chaincode.dsl.model.uri.ChaincodeUriBurst
import ssm.chaincode.dsl.model.uri.compact
import ssm.couchdb.dsl.config.SsmCouchdbConfig
import ssm.couchdb.dsl.model.DocType
import ssm.couchdb.dsl.query.CouchdbChaincodeListQueryFunction
import ssm.couchdb.dsl.query.CouchdbChaincodeListQueryResult
import ssm.couchdb.f2.commons.CouchdbF2Function

class CouchdbChaincodeListQueryFunctionImpl(
	private val config: SsmCouchdbConfig,
) {
	companion object {
		const val DB_LSCC = "_lscc"
	}

	fun couchdbChaincodeListQueryFunction(): CouchdbChaincodeListQueryFunction =
		CouchdbF2Function.function(config) { _, couchdbClient ->
			couchdbClient.cloudant.allDbs.execute()
				.result
				.asFlow()
				.filter { it.contains(DB_LSCC) }
				.flatMapMerge { dbName ->
					val channelId = dbName.removeSuffix(DB_LSCC)
					couchdbClient.fetchAllByDocType(dbName, DocType.Chaincode).map { lscc ->
						lscc._id
					}.map { chaincodeId ->
						ChaincodeUriBurst(
							channelId = channelId,
							chaincodeId = chaincodeId
						).compact()
					}.asFlow()
				}.toList()
				.let {
					CouchdbChaincodeListQueryResult(
						items = it
					)
				}

		}

}
