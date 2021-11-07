package ssm.couchdb.f2.query

import f2.dsl.cqrs.page.Page
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ssm.couchdb.client.SsmCouchdbClient
import ssm.couchdb.dsl.model.DocType
import ssm.couchdb.dsl.query.CouchdbSsmListQuery
import ssm.couchdb.dsl.query.CouchdbSsmListQueryFunction
import ssm.couchdb.dsl.query.CouchdbSsmListQueryResult
import ssm.couchdb.f2.commons.chainCodeDbName

class CouchdbSsmListQueryFunctionImpl(
	private val couchdbClient: SsmCouchdbClient,
) : CouchdbSsmListQueryFunction {

	override suspend fun invoke(msg: Flow<CouchdbSsmListQuery>): Flow<CouchdbSsmListQueryResult> = msg.map { payload ->
		couchdbClient
			.fetchAllByDocType(chainCodeDbName(payload.channelId, payload.chaincodeId), DocType.Ssm)
			.let{ list ->
				CouchdbSsmListQueryResult(
					page = Page(
						list = list,
						total = list.size
					),
					pagination = payload.pagination
				)
			}
	}
}
