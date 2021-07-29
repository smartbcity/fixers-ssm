package ssm.tx.dsl.features.query
import f2.dsl.function.F2Function
import f2.dsl.function.F2FunctionRemote
import ssm.tx.dsl.model.TxSsmSessionId
import ssm.tx.dsl.model.TxSsmSessionState
import kotlin.js.JsExport
import kotlin.js.JsName

typealias TxSsmSessionLogListQueryFunction = F2Function<TxSsmSessionLogListQueryDTO, TxSsmSessionLogListQueryResultDTO>
typealias TxSsmSessionLogListQueryRemoteFunction = F2FunctionRemote<TxSsmSessionLogListQueryDTO, TxSsmSessionLogListQueryResultDTO>


@JsExport
@JsName("TxSsmSessionLogListQueryDTO")
interface TxSsmSessionLogListQueryDTO: TxQueryDTO {
    val sessionId: TxSsmSessionId
    override val ssm: SsmName
    override val bearerToken: String?
}

@JsExport
@JsName("TxSsmSessionLogListQuery")
class TxSsmSessionLogListQuery(
	override val sessionId: TxSsmSessionId,
	override val ssm: SsmName,
	override val bearerToken: String?
): TxSsmSessionLogListQueryDTO


@JsExport
@JsName("TxSsmSessionLogListQueryResultDTO")
interface TxSsmSessionLogListQueryResultDTO{
	val list: List<TxSsmSessionState>
}

@JsExport
@JsName("SsmSessionLogListQueryResult")
class TxSsmSessionLogListQueryResult(
	override val list: List<TxSsmSessionState>
) : TxSsmSessionLogListQueryResultDTO
