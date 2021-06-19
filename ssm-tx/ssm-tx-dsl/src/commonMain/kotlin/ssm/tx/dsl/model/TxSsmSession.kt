package ssm.tx.dsl.model

import ssm.chaincode.dsl.blockchain.Transaction
import ssm.chaincode.dsl.blockchain.TransactionBase
import kotlin.js.JsExport
import kotlin.js.JsName

@JsExport
@JsName("TxSsmSessionDTO")
interface TxSsmSessionDTO {
    val id: TxSsmSessionId
    val currentState: TxSsmSessionStateDTO
    val channel: TxChannelDTO
    val creationTransaction: Transaction?
}

@JsExport
@JsName("TxSsmSession")
class TxSsmSession(
	override val id: TxSsmSessionId,
	override val currentState: TxSsmSessionState,
	override val channel: TxChannel,
	override val creationTransaction: TransactionBase?,
): TxSsmSessionDTO

typealias TxSsmSessionId = String
