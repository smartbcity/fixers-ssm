package ssm.tx.dsl.model

import ssm.chaincode.dsl.blockchain.Transaction
import ssm.chaincode.dsl.blockchain.TransactionBase
import kotlin.js.JsExport
import kotlin.js.JsName

@JsExport
@JsName("TxSsmSessionDTO")
interface TxSsmSessionDTO {
    val id: TxSsmSessionId
    val state: TxSsmSessionStateDTO
    val channel: TxChannelDTO
    val transaction: Transaction?
}

@JsExport
@JsName("TxSsmSession")
class TxSsmSession(
	override val id: TxSsmSessionId,
	override val state: TxSsmSessionState,
	override val channel: TxChannel,
	override val transaction: TransactionBase?,
): TxSsmSessionDTO

typealias TxSsmSessionId = String
