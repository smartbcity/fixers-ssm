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
import ssm.chaincode.dsl.model.ChaincodeId
import ssm.chaincode.dsl.model.ChannelId
import ssm.chaincode.dsl.model.Ssm
import ssm.chaincode.dsl.model.SsmDTO

/**
 * Retrieves the list of ssm stored in the database.
 * @title List SSMs
 * @d2 function
 * @parent [ssm.couchdb.dsl.CouchdbSsmD2Query]
 *
 */
typealias CouchdbSsmListQueryFunction = F2Function<CouchdbSsmListQuery, CouchdbSsmListQueryResult>

/**
 * @d2 query
 * @parent [CouchdbSsmListQueryFunction]
 * @title Parameters
 */
expect interface CouchdbSsmListQueryDTO : PageQueryDTO {
	/**
	 * The unique id of a channel.
	 */
	val channelId: ChannelId
	/**
	 * The unique id of a chaincode.
	 */
	val chaincodeId: ChaincodeId
}

/**
 * @d2 event
 * @parent [CouchdbSsmListQueryFunction]
 * @order 30
 * @title Result
 */
expect interface CouchdbSsmListQueryResultDTO: PageQueryResultDTO<SsmDTO> {
	override val page: PageDTO<SsmDTO>
}

@Serializable
@JsExport
@JsName("CouchdbSsmListQuery")
class CouchdbSsmListQuery(
	override val pagination: OffsetPaginationDTO?,
	override val channelId: ChannelId,
	override val chaincodeId: ChaincodeId,
) : CouchdbSsmListQueryDTO

@Serializable
@JsExport
@JsName("CouchdbSsmListQueryResult")
class CouchdbSsmListQueryResult(
	override val page: Page<Ssm>,
	override val pagination: OffsetPaginationDTO?,
) : CouchdbSsmListQueryResultDTO
