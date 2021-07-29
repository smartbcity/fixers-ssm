package ssm.tx.dsl.model

import kotlinx.serialization.Serializable
import ssm.chaincode.dsl.blockchain.TransactionDTO
import ssm.chaincode.dsl.blockchain.Transaction
import kotlin.js.JsExport
import kotlin.js.JsName

expect interface TxSsmSessionDTO {
    val id: TxSsmSessionId
    val state: TxSsmSessionStateDTO
    val channel: TxChannelDTO
    val transaction: TransactionDTO?
}

@Serializable
@JsExport
@JsName("TxSsmSession")
class TxSsmSession(
	override val id: TxSsmSessionId,
	override val state: TxSsmSessionState,
	override val channel: TxChannel,
	override val transaction: Transaction?,
): TxSsmSessionDTO

typealias TxSsmSessionId = String
