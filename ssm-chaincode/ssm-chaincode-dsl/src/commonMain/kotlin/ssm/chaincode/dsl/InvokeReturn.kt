package ssm.chaincode.dsl

import kotlinx.serialization.Serializable
import ssm.chaincode.dsl.blockchain.TransactionId
import kotlin.js.JsExport
import kotlin.js.JsName

expect interface InvokeReturnDTO {
	val status: String
	val info: String
	val transactionId: String
}

/**
 * Response of the API after a command has been executed
 * @d2 model
 */
@Serializable
@JsExport
@JsName("InvokeReturn")
class InvokeReturn(
	/**
	 * Resulting status of the transaction
	 * @example "SUCCESS"
	 */
	val status: String,

	/**
	 * Additional information about the transaction
	 * @example ""
	 */
	val info: String,

	/**
	 * Identifier of the transaction
	 * @example [ssm.chaincode.dsl.blockchain.Transaction.transactionId]
	 */
	val transactionId: TransactionId
)