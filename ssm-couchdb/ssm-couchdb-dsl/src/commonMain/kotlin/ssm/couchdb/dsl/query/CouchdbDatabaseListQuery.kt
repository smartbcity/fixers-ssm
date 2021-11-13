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
import ssm.couchdb.dsl.model.Database
import ssm.couchdb.dsl.model.DatabaseDTO

/**
 * @title Retrieves the list of databases.
 * @d2 query
 * @order 10
 * @parent [ssm.couchdb.dsl.CouchdbSsmD2Query]
 */
typealias CouchdbDatabaseListQueryFunction = F2Function<CouchdbDatabaseListQueryDTO, CouchdbDatabaseListQueryResultDTO>

/**
 * @title Parameters
 * @d2 model
 * @parent [CouchdbDatabaseListQueryFunction]
 */
expect interface CouchdbDatabaseListQueryDTO : PageQueryDTO {
	/**
	 * The unique id of a channel.
	 */
	val channelId: ChannelId?

	/**
	 * The unique id of a chaincode.
	 */
	val chaincodeId: ChaincodeId?
}

/**
 * @d2 model
 * @title Results
 * @parent [CouchdbDatabaseListQueryFunction]
 */
expect interface CouchdbDatabaseListQueryResultDTO : PageQueryResultDTO<DatabaseDTO> {
	override val page: PageDTO<DatabaseDTO>
}

@Serializable
@JsExport
@JsName("CouchdbDatabaseListQuery")
class CouchdbDatabaseListQuery(
	override val pagination: OffsetPaginationDTO? = null,
	override val channelId: ChannelId? = null,
	override val chaincodeId: ChaincodeId? = null,
) : CouchdbDatabaseListQueryDTO

@Serializable
@JsExport
@JsName("CouchdbDatabaseListQueryResult")
class CouchdbDatabaseListQueryResult(
	override val page: Page<Database>,
	override val pagination: OffsetPaginationDTO?,
) : CouchdbDatabaseListQueryResultDTO
