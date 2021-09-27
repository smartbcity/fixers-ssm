package ssm.couchdb.dsl.query

import f2.dsl.cqrs.page.OffsetPagination
import f2.dsl.cqrs.page.OffsetPaginationDTO
import f2.dsl.cqrs.page.Page
import f2.dsl.cqrs.page.PageDTO
import f2.dsl.cqrs.page.PageQueryDTO
import f2.dsl.cqrs.page.PageQueryResultDTO
import f2.dsl.fnc.F2Function
import kotlin.js.JsExport
import kotlin.js.JsName
import kotlinx.serialization.Serializable
import ssm.chaincode.dsl.model.ChaincodeId
import ssm.chaincode.dsl.model.ChannelId
import ssm.chaincode.dsl.model.SsmName
import ssm.chaincode.dsl.model.SsmSessionState
import ssm.chaincode.dsl.model.SsmSessionStateDTO

/**
 * Retrieve the list of all known sessions of a given SSM
 * @d2 function
 * @parent [ssm.couchdb.dsl.SsmCouchdbD2Query]
 * @order 40
 * @title List Sessions
 */
typealias CouchdbSsmSessionStateListQueryFunction = F2Function<CouchdbSsmSessionStateListQueryDTO, CouchdbSsmSessionStateListQueryResultDTO>

expect interface CouchdbSsmSessionStateListQueryDTO : PageQueryDTO {
	/**
	 * The unique id of a channel.
	 */
	val channelId: ChannelId
	/**
	 * The unique id of a chaincode.
	 */
	val chaincodeId: ChaincodeId
	/**
	 * The unique identifier of a ssm.
	 */
	val ssm: SsmName?
}

expect interface CouchdbSsmSessionStateListQueryResultDTO : PageQueryResultDTO<SsmSessionStateDTO> {
	/**
	 * Retrieved sessions
	 */
	override val page: PageDTO<SsmSessionStateDTO>
}

/**
 * @d2 query
 * @parent [CouchdbSsmSessionStateListQueryFunction]
 * @title List Sessions: Parameters
 */
@Serializable
@JsExport
@JsName("CouchdbSsmSessionStateListQuery")
class CouchdbSsmSessionStateListQuery(
	override val channelId: ChannelId,
	override val chaincodeId: ChaincodeId,
	override val ssm: SsmName?,
	override val pagination: OffsetPagination? = null,
) : CouchdbSsmSessionStateListQueryDTO

/**
 * @d2 event
 * @parent [CouchdbSsmSessionStateListQueryFunction]
 * @title List Sessions: Result
 */
@Serializable
@JsExport
@JsName("CouchdbSsmSessionStateListQueryResult")
class CouchdbSsmSessionStateListQueryResult(
	override val page: Page<SsmSessionState>,
	override val pagination: OffsetPaginationDTO?,
) : CouchdbSsmSessionStateListQueryResultDTO
