package ssm.couchdb.dsl.query

import f2.dsl.fnc.F2Function
import kotlin.js.JsExport
import kotlin.js.JsName
import kotlinx.serialization.Serializable
import ssm.chaincode.dsl.Ssm
import ssm.couchdb.dsl.CdbQueryDTO

/**
 * Retrieve the list of all known SSMs
 * @d2 function
 * @parent [ssm.couchdb.dsl.Database]
 * @order 10
 * @title List SSMs
 */
typealias CdbSsmListQueryFunction = F2Function<CdbSsmListQueryDTO, CdbSsmListQueryResultDTO>

expect interface CdbSsmListQueryDTO : CdbQueryDTO {
	/**
	 * Name of the database to query on
	 * @example [ssm.couchdb.dsl.Database.name]
	 */
	val dbName: String
	override val dbConfig: String
}

/**
 * @d2 query
 * @parent [CdbSsmListQueryFunction]
 * @title List SSMs: Parameters
 */
@Serializable
@JsExport
@JsName("CdbSsmListQuery")
class CdbSsmListQuery(
	override val dbName: String,
	override val dbConfig: String,
) : CdbSsmListQueryDTO

expect interface CdbSsmListQueryResultDTO {
	/**
	 * Retrieved SSMs
	 */
	val ssmList: List<Ssm>
}

/**
 * @d2 event
 * @parent [CdbSsmListQueryFunction]
 * @title List SSMs: Result
 */
@Serializable
@JsExport
@JsName("CdbSsmListQueryResult")
class CdbSsmListQueryResult(
	override val ssmList: List<Ssm>,
) : CdbSsmListQueryResultDTO
