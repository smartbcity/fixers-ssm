package ssm.tx.dsl.features.query

import f2.dsl.fnc.F2Function
import kotlin.js.JsExport
import kotlin.js.JsName
import kotlinx.serialization.Serializable
import ssm.tx.dsl.model.TxSsmSession
import ssm.tx.dsl.model.TxSsmSessionDTO

/**
 * Retrieves a given session
 * @d2 function
 * @parent [TxSsmSession]
 * @order 10
 * @title Get Session
 */
typealias TxSsmSessionGetQueryFunction = F2Function<TxSsmSessionGetQueryDTO, TxSsmSessionGetQueryResultDTO>

expect interface TxSsmSessionGetQueryDTO : TxQueryDTO {
	/**
	 * Identifier of the session to retrieve
	 * @example [TxSsmSession.id]
	 */
	val sessionId: String
	override val ssm: SsmName
	override val bearerToken: String?
}

/**
 * @d2 query
 * @parent [TxSsmSessionGetQueryFunction]
 * @title Get Session: Parameters
 */
@Serializable
@JsExport
@JsName("TxSsmSessionGetQuery")
class TxSsmSessionGetQuery(
	override val sessionId: String,
	override val ssm: SsmName,
	override val bearerToken: String?,
) : TxSsmSessionGetQueryDTO

expect interface TxSsmSessionGetQueryResultDTO {
	/**
	 * The retrieved session if it exists
	 */
	val session: TxSsmSessionDTO?
}

/**
 * @d2 event
 * @parent [TxSsmSessionGetQueryFunction]
 * @title Get Session: Result
 */
@Serializable
@JsExport
@JsName("TxSsmSessionGetQueryResult")
class TxSsmSessionGetQueryResult(
	override val session: TxSsmSession?,
) : TxSsmSessionGetQueryResultDTO
