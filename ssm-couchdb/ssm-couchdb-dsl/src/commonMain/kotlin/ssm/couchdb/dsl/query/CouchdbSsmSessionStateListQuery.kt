package ssm.couchdb.dsl.query

import f2.dsl.cqrs.page.OffsetPaginationDTO
import f2.dsl.cqrs.page.Page
import f2.dsl.cqrs.page.PageDTO
import f2.dsl.cqrs.page.PageQueryDTO
import f2.dsl.cqrs.page.PageQueryResultDTO
import f2.dsl.fnc.F2Function
import kotlin.js.JsExport
import kotlin.js.JsName
import kotlinx.serialization.Serializable
import ssm.chaincode.dsl.model.SsmName
import ssm.chaincode.dsl.model.SsmSessionState
import ssm.chaincode.dsl.model.SsmSessionStateDTO
import ssm.chaincode.dsl.model.uri.ChaincodeUri

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
	 * The unique uri of a channel.
	 */
	val chaincodeUri: ChaincodeUri
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
	override val pagination: OffsetPaginationDTO? = null,
	override val chaincodeUri: ChaincodeUri,
	override val ssm: SsmName? = null
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
