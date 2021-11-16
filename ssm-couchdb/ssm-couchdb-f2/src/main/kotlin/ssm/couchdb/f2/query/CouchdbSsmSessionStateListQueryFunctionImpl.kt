package ssm.couchdb.f2.query

import f2.dsl.cqrs.page.Page
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ssm.chaincode.dsl.model.SsmSessionStateDTO
import ssm.chaincode.dsl.model.uri.burstChaincode
import ssm.couchdb.client.CouchdbSsmClient
import ssm.couchdb.dsl.model.DocType
import ssm.couchdb.dsl.query.CouchdbSsmSessionStateListQueryDTO
import ssm.couchdb.dsl.query.CouchdbSsmSessionStateListQueryFunction
import ssm.couchdb.dsl.query.CouchdbSsmSessionStateListQueryResult
import ssm.couchdb.dsl.query.CouchdbSsmSessionStateListQueryResultDTO
import ssm.couchdb.f2.commons.chainCodeDbName

class CouchdbSsmSessionStateListQueryFunctionImpl(
	private val couchdbClient: CouchdbSsmClient,
) : CouchdbSsmSessionStateListQueryFunction {

	override suspend fun invoke(msg: Flow<CouchdbSsmSessionStateListQueryDTO>): Flow<CouchdbSsmSessionStateListQueryResultDTO> =
		msg.map { payload ->
			val filters = payload.ssm?.let { ssm ->
				mapOf(SsmSessionStateDTO::ssm.name to ssm)
			} ?: emptyMap()
			couchdbClient.fetchAllByDocType(
				chainCodeDbName(
					payload.chaincodeUri.burstChaincode()!!.channelId,
					payload.chaincodeUri.burstChaincode()!!.chaincodeId
				), DocType.State, filters
			).let { list ->
				CouchdbSsmSessionStateListQueryResult(
					page = Page(
						list = list,
						total = list.size
					),
					pagination = payload.pagination
				)
			}
		}
}
