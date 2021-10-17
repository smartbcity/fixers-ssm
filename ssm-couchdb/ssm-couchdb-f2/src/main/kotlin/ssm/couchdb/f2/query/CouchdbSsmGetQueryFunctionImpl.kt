package ssm.couchdb.f2.query

import ssm.chaincode.dsl.model.uri.SsmUriBurst
import ssm.chaincode.dsl.model.uri.compact
import ssm.couchdb.dsl.config.SsmCouchdbConfig
import ssm.couchdb.dsl.model.DocType
import ssm.couchdb.dsl.query.CouchdbSsmGetQueryFunction
import ssm.couchdb.dsl.query.CouchdbSsmGetQueryResult
import ssm.couchdb.f2.commons.CouchdbF2Function
import ssm.couchdb.f2.commons.chainCodeDbName

class CouchdbSsmGetQueryFunctionImpl(
	private val config: SsmCouchdbConfig,
) {

	fun couchdbSsmGetQueryFunction(): CouchdbSsmGetQueryFunction = CouchdbF2Function.function(config) { query, couchdbClient ->
		couchdbClient
			.fetchOneByDocTypeAndName(chainCodeDbName(query.channelId, query.chaincodeId), DocType.Ssm, query.ssmName)
			.let{ item ->
				CouchdbSsmGetQueryResult(
					item = item,
					uri = SsmUriBurst(
						channelId = query.channelId,
						chaincodeId = query.chaincodeId,
						ssmName = query.ssmName,
					).compact()
				)
			}
	}
}
