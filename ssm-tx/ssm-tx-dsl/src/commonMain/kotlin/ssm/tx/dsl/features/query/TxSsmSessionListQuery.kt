package ssm.tx.dsl.features.query

import ssm.tx.dsl.model.TxSsmSessionDTO
import ssm.tx.dsl.model.TxSsmSession
import f2.dsl.function.F2Function
import f2.dsl.function.F2FunctionRemote
import kotlin.js.JsExport
import kotlin.js.JsName

typealias TxSsmSessionListQueryFunction = F2Function<TxSsmSessionListQueryDTO, TxSsmSessionListQueryResultDTO>
typealias TxSsmSessionListQueryRemoteFunction = F2FunctionRemote<TxSsmSessionListQueryDTO, TxSsmSessionListQueryResultDTO>


@JsExport
@JsName("TxSsmSessionListQueryDTO")
interface TxSsmSessionListQueryDTO: TxQueryDTO {
    override val ssm: String
}

@JsExport
@JsName("TxSsmSessionListQuery")
class TxSsmSessionListQuery(
    override val ssm: String,
    override val bearerToken: String?
): TxSsmSessionListQueryDTO


@JsExport
@JsName("TxSsmSessionListQueryResultDTO")
interface TxSsmSessionListQueryResultDTO{
    val list: List<TxSsmSessionDTO>
}

@JsExport
@JsName("TxSsmSessionListQueryResult")
class TxSsmSessionListQueryResult(
    override val list: List<TxSsmSession>
): TxSsmSessionListQueryResultDTO
