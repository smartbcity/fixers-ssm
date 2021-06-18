package ssm.chaincode.dsl

import kotlinx.serialization.Serializable
import ssm.chaincode.dsl.blockchain.TransactionId
import kotlin.js.JsExport
import kotlin.js.JsName

@Serializable
@JsExport
@JsName("SsmSessionStateLog")
data class SsmSessionStateLog(
	val txId: TransactionId,
	val state: SsmSessionStateBase
)