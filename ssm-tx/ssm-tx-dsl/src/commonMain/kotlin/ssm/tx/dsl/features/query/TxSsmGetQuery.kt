package ssm.tx.dsl.features.query

import f2.dsl.function.F2Function
import f2.dsl.function.F2FunctionRemote
import ssm.tx.dsl.model.TxSsm
import kotlin.js.JsExport
import kotlin.js.JsName

typealias TxSsmGetQueryFunction = F2Function<TxSsmGetQueryDTO, TxSsmGetQueryResultDTO>
typealias TxSsmGetQueryRemoteFunction = F2FunctionRemote<TxSsmGetQueryDTO, TxSsmGetQueryResultDTO>


@JsExport
@JsName("TxSsmGetQueryDTO")
interface TxSsmGetQueryDTO: TxQueryDTO {
    override val ssm: SsmName
    override val bearerToken: String?
}

@JsExport
@JsName("TxSsmGetQuery")
class TxSsmGetQuery(
	override val ssm: SsmName,
	override val bearerToken: String?
): TxSsmGetQueryDTO

@JsExport
@JsName("TxSsmGetQueryResultDTO")
interface TxSsmGetQueryResultDTO{
	val ssm: TxSsm?
}

@JsExport
@JsName("TxSsmGetQueryResult")
class TxSsmGetQueryResult(
	override val ssm: TxSsm?
): TxSsmGetQueryResultDTO
