package ssm.chaincode.dsl

import kotlinx.serialization.Serializable
import ssm.chaincode.dsl.blockchain.TransactionId
import kotlin.js.JsExport
import kotlin.js.JsName

expect interface SsmSessionStateLogDTO {
	/**
	 * Id of the [Transaction][ssm.chaincode.dsl.blockchain.Transaction] the state is originated from
	 * @example [ssm.chaincode.dsl.blockchain.Transaction.transactionId]
	 */
	val txId: TransactionId

	/**
	 * The state generated with the transaction
	 */
	val state: SsmSessionStateDTO
}

/**
 * Associates a session state with the actual blockchain transaction that lead to it
 * @d2 model
 * @parent [SsmSession]
 * @order 20
 */
@Serializable
@JsExport
@JsName("SsmSessionStateLog")
data class SsmSessionStateLog(
	override val txId: TransactionId,
	override val state: SsmSessionState
): SsmSessionStateLogDTO