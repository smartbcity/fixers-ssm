package ssm.tx.dsl.features.query

import ssm.tx.dsl.model.TxSsmSession
import f2.dsl.fnc.F2Function
import kotlinx.serialization.Serializable
import ssm.tx.dsl.model.TxSsmSessionDTO
import kotlin.js.JsExport
import kotlin.js.JsName

typealias TxSsmSessionGetQueryFunction = F2Function<TxSsmSessionGetQueryDTO, TxSsmSessionGetQueryResultDTO>

expect interface TxSsmSessionGetQueryDTO: TxQueryDTO {
    val sessionId: String
    override val ssm: SsmName
    override val bearerToken: String?
}

@Serializable
@JsExport
@JsName("TxSsmSessionGetQuery")
class TxSsmSessionGetQuery(
	override val sessionId: String,
	override val ssm: SsmName,
	override val bearerToken: String?
): TxSsmSessionGetQueryDTO


expect interface TxSsmSessionGetQueryResultDTO{
	val session: TxSsmSessionDTO?
}

@Serializable
@JsExport
@JsName("TxSsmSessionGetQueryResult")
class TxSsmSessionGetQueryResult(
	override val session: TxSsmSession?,
): TxSsmSessionGetQueryResultDTO
