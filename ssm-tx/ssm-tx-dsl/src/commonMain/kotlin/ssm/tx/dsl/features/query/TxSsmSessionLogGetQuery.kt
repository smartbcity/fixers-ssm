package ssm.tx.dsl.features.query

import f2.dsl.fnc.F2Function
import kotlinx.serialization.Serializable
import ssm.chaincode.dsl.blockchain.TransactionId
import ssm.tx.dsl.model.TxSsmSessionId
import ssm.tx.dsl.model.TxSsmSessionStateDTO
import kotlin.js.JsExport
import kotlin.js.JsName

typealias TxSsmSessionLogGetQueryFunction = F2Function<TxSsmSessionLogGetQueryDTO, TxSsmSessionLogGetQueryResultDTO>

expect interface TxSsmSessionLogGetQueryDTO: TxQueryDTO {
    val sessionId: TxSsmSessionId
    val txId: TransactionId
    override val ssm: SsmName
    override val bearerToken: String?
}

@Serializable
@JsExport
@JsName("TxSsmSessionLogGetQuery")
class TxSsmSessionLogGetQuery(
	override val sessionId: TxSsmSessionId,
	override val txId: TransactionId,
	override val ssm: SsmName,
	override val bearerToken: String?
): TxSsmSessionLogGetQueryDTO

expect interface TxSsmSessionLogGetQueryResultDTO{
	val ssmSessionState: TxSsmSessionStateDTO?
}

@Serializable
@JsExport
@JsName("TxSsmSessionLogGetQueryResult")
class TxSsmSessionLogGetQueryResult(
	override val ssmSessionState: TxSsmSessionStateDTO?
): TxSsmSessionLogGetQueryResultDTO
