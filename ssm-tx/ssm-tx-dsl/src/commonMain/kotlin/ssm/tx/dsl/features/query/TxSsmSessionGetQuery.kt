package ssm.tx.dsl.features.query

import ssm.tx.dsl.model.TxSsmSession
import f2.dsl.function.F2Function
import f2.dsl.function.F2FunctionRemote
import ssm.tx.dsl.model.TxSsmSessionDTO
import kotlin.js.JsExport
import kotlin.js.JsName

typealias TxSsmSessionGetQueryFunction = F2Function<TxSsmSessionGetQueryDTO, TxSsmSessionQueryGetResultDTO>
typealias TxSsmSessionGetQueryRemoteFunction = F2FunctionRemote<TxSsmSessionGetQueryDTO, TxSsmSessionQueryGetResultDTO>


@JsExport
@JsName("TxSsmSessionGetQueryDTO")
interface TxSsmSessionGetQueryDTO: TxQueryDTO {
    val sessionId: String
    override val ssm: SsmName
    override val bearerToken: String?
}

@JsExport
@JsName("TxSsmSessionGetQuery")
class TxSsmSessionGetQuery(
	override val sessionId: String,
	override val ssm: SsmName,
	override val bearerToken: String?
): TxSsmSessionGetQueryDTO


@JsExport
@JsName("TxSsmSessionQueryGetResultDTO")
interface TxSsmSessionQueryGetResultDTO{
	val session: TxSsmSessionDTO?
}

@JsExport
@JsName("TxSsmSessionGetQueryResult")
class TxSsmSessionGetQueryResult(
	override val session: TxSsmSession?,
): TxSsmSessionQueryGetResultDTO
