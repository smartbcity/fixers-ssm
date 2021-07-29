package ssm.tx.dsl.features.query

import f2.dsl.function.F2Function
import f2.dsl.function.F2FunctionRemote
import ssm.chaincode.dsl.blockchain.TransactionId
import ssm.tx.dsl.model.TxSsmSessionId
import ssm.tx.dsl.model.TxSsmSessionStateDTO
import kotlin.js.JsExport
import kotlin.js.JsName

typealias TxSsmSessionLogGetQueryFunction = F2Function<TxSsmSessionLogGetQueryDTO, TxSsmSessionLogGetQueryResultDTO>
typealias TxSsmSessionLogGetQueryRemoteFunction = F2FunctionRemote<TxSsmSessionLogGetQueryDTO, TxSsmSessionLogGetQueryResultDTO>

@JsExport
@JsName("TxSsmSessionLogGetQueryDTO")
interface TxSsmSessionLogGetQueryDTO: TxQueryDTO {
    val sessionId: TxSsmSessionId
    val txId: TransactionId
    override val ssm: SsmName
    override val bearerToken: String?
}

@JsExport
@JsName("TxSsmSessionLogGetQuery")
class TxSsmSessionLogGetQuery(
	override val sessionId: TxSsmSessionId,
	override val txId: TransactionId,
	override val ssm: SsmName,
	override val bearerToken: String?
): TxSsmSessionLogGetQueryDTO

@JsExport
@JsName("TxSsmSessionLogGetQueryResultDTO")
interface TxSsmSessionLogGetQueryResultDTO{
	val ssmSessionState: TxSsmSessionStateDTO?
}

@JsExport
@JsName("TxSsmSessionLogGetQueryResult")
class TxSsmSessionLogGetQueryResult(
	override val ssmSessionState: TxSsmSessionStateDTO?
): TxSsmSessionLogGetQueryResultDTO
