package ssm.tx.dsl.features.query

import ssm.tx.dsl.model.TxSsmDTO
import f2.dsl.fnc.F2Function
import kotlinx.serialization.Serializable
import kotlin.js.JsExport
import kotlin.js.JsName

typealias TxSsmListQueryFunction = F2Function<TxSsmListQueryDTO, TxSsmListQueryResultDTO>

expect interface TxSsmListQueryDTO

@Serializable
@JsExport
@JsName("TxSsmListQuery")
class TxSsmListQuery(
): TxSsmListQueryDTO

expect interface TxSsmListQueryResultDTO{
    val list: List<TxSsmDTO>
}

@Serializable
@JsExport
@JsName("TxSsmListQueryResult")
class TxSsmListQueryResult(
    override val list: List<TxSsmDTO>
): TxSsmListQueryResultDTO
