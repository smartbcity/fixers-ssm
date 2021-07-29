package ssm.tx.dsl.features.query

import f2.dsl.fnc.F2Function
import kotlinx.serialization.Serializable
import ssm.tx.dsl.model.TxSsmSessionId
import ssm.tx.dsl.model.TxSsmSessionState
import kotlin.js.JsExport
import kotlin.js.JsName

typealias TxSsmSessionLogListQueryFunction = F2Function<TxSsmSessionLogListQueryDTO, TxSsmSessionLogListQueryResultDTO>


expect interface TxSsmSessionLogListQueryDTO : TxQueryDTO {
	val sessionId: TxSsmSessionId
	override val ssm: SsmName
	override val bearerToken: String?
}

@Serializable
@JsExport
@JsName("TxSsmSessionLogListQuery")
class TxSsmSessionLogListQuery(
	override val sessionId: TxSsmSessionId,
	override val ssm: SsmName,
	override val bearerToken: String?,
) : TxSsmSessionLogListQueryDTO


expect interface TxSsmSessionLogListQueryResultDTO {
	val list: List<TxSsmSessionState>
}

@Serializable
@JsExport
@JsName("SsmSessionLogListQueryResult")
class TxSsmSessionLogListQueryResult(
	override val list: List<TxSsmSessionState>,
) : TxSsmSessionLogListQueryResultDTO
