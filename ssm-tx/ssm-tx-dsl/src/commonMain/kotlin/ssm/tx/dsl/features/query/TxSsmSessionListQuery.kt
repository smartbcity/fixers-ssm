package ssm.tx.dsl.features.query

import f2.dsl.fnc.F2Function
import kotlinx.serialization.Serializable
import ssm.tx.dsl.model.TxSsmSession
import ssm.tx.dsl.model.TxSsmSessionDTO
import kotlin.js.JsExport
import kotlin.js.JsName

typealias TxSsmSessionListQueryFunction = F2Function<TxSsmSessionListQueryDTO, TxSsmSessionListQueryResultDTO>


expect interface TxSsmSessionListQueryDTO : TxQueryDTO {
	override val ssm: String
}

@Serializable
@JsExport
@JsName("TxSsmSessionListQuery")
class TxSsmSessionListQuery(
	override val ssm: String,
	override val bearerToken: String?,
) : TxSsmSessionListQueryDTO


expect interface TxSsmSessionListQueryResultDTO {
	val list: List<TxSsmSessionDTO>
}

@Serializable
@JsExport
@JsName("TxSsmSessionListQueryResult")
class TxSsmSessionListQueryResult(
	override val list: List<TxSsmSession>,
) : TxSsmSessionListQueryResultDTO
