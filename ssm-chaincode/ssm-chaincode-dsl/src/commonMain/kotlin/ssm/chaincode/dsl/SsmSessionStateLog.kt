package ssm.chaincode.dsl

import kotlinx.serialization.Serializable
import ssm.chaincode.dsl.blockchain.TransactionId
import kotlin.js.JsExport
import kotlin.js.JsName

expect interface SsmSessionStateLogDTO {
	val txId: TransactionId
	val state: SsmSessionStateDTO
}

@Serializable
@JsExport
@JsName("SsmSessionStateLog")
data class SsmSessionStateLog(
	override val txId: TransactionId,
	override val state: SsmSessionState
): SsmSessionStateLogDTO