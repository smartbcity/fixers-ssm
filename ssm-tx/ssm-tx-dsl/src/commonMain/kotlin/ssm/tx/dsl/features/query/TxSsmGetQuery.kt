package ssm.tx.dsl.features.query

import f2.dsl.fnc.F2Function
import kotlinx.serialization.Serializable
import ssm.tx.dsl.model.TxSsm
import kotlin.js.JsExport
import kotlin.js.JsName

typealias TxSsmGetQueryFunction = F2Function<TxSsmGetQueryDTO, TxSsmGetQueryResultDTO>

expect interface TxSsmGetQueryDTO: TxQueryDTO {
    override val ssm: SsmName
    override val bearerToken: String?
}

@Serializable
@JsExport
@JsName("TxSsmGetQuery")
class TxSsmGetQuery(
	override val ssm: SsmName,
	override val bearerToken: String?
): TxSsmGetQueryDTO

expect interface TxSsmGetQueryResultDTO{
	val ssm: TxSsm?
}

@Serializable
@JsExport
@JsName("TxSsmGetQueryResult")
class TxSsmGetQueryResult(
	override val ssm: TxSsm?
): TxSsmGetQueryResultDTO
