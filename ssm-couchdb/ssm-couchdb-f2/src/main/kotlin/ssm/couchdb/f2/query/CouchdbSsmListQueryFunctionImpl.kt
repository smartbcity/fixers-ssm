package ssm.couchdb.f2.query

import f2.dsl.cqrs.page.Page
import ssm.couchdb.dsl.config.CouchdbConfig
import ssm.couchdb.dsl.model.DocType
import ssm.couchdb.dsl.query.CouchdbSsmListQueryFunction
import ssm.couchdb.dsl.query.CouchdbSsmListQueryResult
import ssm.couchdb.f2.commons.CouchdbF2Function
import ssm.couchdb.f2.commons.chainCodeDbName

class CouchdbSsmListQueryFunctionImpl(
	private val config: CouchdbConfig,
) {

	fun couchdbSsmListQueryFunction(): CouchdbSsmListQueryFunction = CouchdbF2Function.function(config) { cmd, couchdbClient ->
		couchdbClient
			.fetchAllByDocType(chainCodeDbName(cmd.channelId, cmd.chaincodeId), DocType.Ssm)
			.let{ list ->
				CouchdbSsmListQueryResult(
					page = Page(
						list = list,
						total = list.size
					),
					pagination = cmd.pagination
				)
			}
	}
}
