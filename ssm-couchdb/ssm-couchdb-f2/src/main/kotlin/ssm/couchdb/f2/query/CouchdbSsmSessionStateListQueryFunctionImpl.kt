package ssm.couchdb.f2.query

import f2.dsl.cqrs.page.Page
import ssm.chaincode.dsl.SsmSessionStateDTO
import ssm.couchdb.dsl.config.CouchdbConfig
import ssm.couchdb.dsl.model.DocType
import ssm.couchdb.dsl.query.CouchdbSsmSessionStateListQueryFunction
import ssm.couchdb.dsl.query.CouchdbSsmSessionStateListQueryResult
import ssm.couchdb.f2.commons.CouchdbF2Function
import ssm.couchdb.f2.commons.chainCodeDbName

class CouchdbSsmSessionStateListQueryFunctionImpl(
	private val config: CouchdbConfig,
) {

	fun couchdbSsmSessionStateListQueryFunction(): CouchdbSsmSessionStateListQueryFunction =
		CouchdbF2Function.function(config) { cmd, couchdbClient ->
			val filters = cmd.ssm?.let { ssm ->
				mapOf(SsmSessionStateDTO::ssm.name to ssm)
			} ?: emptyMap()
			couchdbClient.fetchAllByDocType(chainCodeDbName(cmd.channelId, cmd.chaincodeId), DocType.State, filters)
				.let { list ->
					CouchdbSsmSessionStateListQueryResult(
						page = Page(
							list = list,
							total = list.size
						),
						pagination = cmd.pagination
					)
				}
		}

}
