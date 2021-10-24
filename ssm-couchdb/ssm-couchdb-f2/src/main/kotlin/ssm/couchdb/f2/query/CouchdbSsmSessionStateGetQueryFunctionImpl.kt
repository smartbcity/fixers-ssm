package ssm.couchdb.f2.query

import ssm.chaincode.dsl.model.SsmSessionStateDTO
import ssm.chaincode.dsl.model.uri.burstSsmUri
import ssm.couchdb.dsl.config.SsmCouchdbConfig
import ssm.couchdb.dsl.model.DocType
import ssm.couchdb.dsl.query.CouchdbSsmSessionStateGetQueryFunction
import ssm.couchdb.dsl.query.CouchdbSsmSessionStateGetQueryResult
import ssm.couchdb.f2.commons.CouchdbF2Function
import ssm.couchdb.f2.commons.chainCodeDbName

class CouchdbSsmSessionStateGetQueryFunctionImpl(
	private val config: SsmCouchdbConfig,
) {

	fun couchdbSsmSessionStateGetQueryFunction(): CouchdbSsmSessionStateGetQueryFunction =
		CouchdbF2Function.function(config) { query, couchdbClient ->
			val ssm = query.ssmUri.burstSsmUri()!!
			val filters = mapOf(SsmSessionStateDTO::session.name to query.sessionName)
			couchdbClient.fetchAllByDocType(chainCodeDbName(ssm.channelId, ssm.chaincodeId), DocType.State, filters)
				.let { list ->
					CouchdbSsmSessionStateGetQueryResult(
						item = list.first()
					)
				}
		}
}
