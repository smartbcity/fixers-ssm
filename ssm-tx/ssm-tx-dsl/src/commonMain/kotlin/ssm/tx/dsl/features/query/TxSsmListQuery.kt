package ssm.tx.dsl.features.query

import ssm.tx.dsl.model.TxSsmDTO
import f2.dsl.function.F2Function
import f2.dsl.function.F2FunctionRemote
import kotlin.js.JsExport
import kotlin.js.JsName

typealias TxSsmListQueryFunction = F2Function<TxSsmListQueryDTO, TxSsmListQueryResultDTO>
typealias TxSsmListQueryRemoteFunction = F2FunctionRemote<TxSsmListQueryDTO, TxSsmListQueryResultDTO>

@JsExport
@JsName("TxSsmListQueryDTO")
interface TxSsmListQueryDTO {
}

@JsExport
@JsName("TxSsmListQuery")
class TxSsmListQuery(
): TxSsmListQueryDTO

@JsExport
@JsName("TxSsmListQueryResultDTO")
interface TxSsmListQueryResultDTO{
    val list: List<TxSsmDTO>
}

@JsExport
@JsName("TxSsmListQueryResult")
class TxSsmListQueryResult(
    override val list: List<TxSsmDTO>
): TxSsmListQueryResultDTO
