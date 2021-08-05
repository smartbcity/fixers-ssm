package ssm.tx.dsl.features.query

import f2.dsl.fnc.F2Function
import kotlinx.serialization.Serializable
import ssm.tx.dsl.model.TxSsmSessionId
import ssm.tx.dsl.model.TxSsmSessionState
import kotlin.js.JsExport
import kotlin.js.JsName

/**
 * Retrieve all the logs of a given SSM session
 * @d2 function
 * @parent [ssm.tx.dsl.model.TxSsmSession]
 * @order 40
 * @title List Session Logs
 */
typealias TxSsmSessionLogListQueryFunction = F2Function<TxSsmSessionLogListQueryDTO, TxSsmSessionLogListQueryResultDTO>

expect interface TxSsmSessionLogListQueryDTO : TxQueryDTO {
	/**
	 * Identifier of the session to retrieve
	 * @example [ssm.tx.dsl.model.TxSsmSession.id]
	 */
	val sessionId: TxSsmSessionId
	override val ssm: SsmName
	override val bearerToken: String?
}

/**
 * @d2 query
 * @parent [TxSsmSessionLogListQueryFunction]
 * @title List Session Logs: Parameters
 */
@Serializable
@JsExport
@JsName("TxSsmSessionLogListQuery")
class TxSsmSessionLogListQuery(
	override val sessionId: TxSsmSessionId,
	override val ssm: SsmName,
	override val bearerToken: String?,
) : TxSsmSessionLogListQueryDTO


expect interface TxSsmSessionLogListQueryResultDTO {
	/**
	 * All retrieved logs of the given session
	 */
	val list: List<TxSsmSessionState>
}

/**
 * @d2 event
 * @parent [TxSsmSessionLogListQueryFunction]
 * @title List Session Logs: Result
 */
@Serializable
@JsExport
@JsName("SsmSessionLogListQueryResult")
class TxSsmSessionLogListQueryResult(
	override val list: List<TxSsmSessionState>,
) : TxSsmSessionLogListQueryResultDTO
