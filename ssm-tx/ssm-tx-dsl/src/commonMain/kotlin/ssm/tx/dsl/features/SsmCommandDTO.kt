package ssm.tx.dsl.features

import f2.dsl.cqrs.Command
import f2.dsl.cqrs.Event
import kotlin.js.JsExport
import kotlin.js.JsName
import kotlinx.serialization.Serializable
import ssm.chaincode.dsl.blockchain.TransactionId
import ssm.chaincode.dsl.model.uri.ChaincodeUri
import ssm.chaincode.dsl.model.uri.ChaincodeUriDTO

interface SsmCommandDTO : Command {
	/**
	 * The uri of a chaincode.
	 */
	val chaincodeUri: ChaincodeUriDTO
}


interface SsmCommandResultDTO : Event {
	/**
	 * Identifier of the transaction
	 * @example [ssm.chaincode.dsl.blockchain.Transaction.transactionId]
	 */
	val transactionId: TransactionId
}


/**
 * Response of the API after a command has been executed
 * @d2 model
 * @parent [ssm.chaincode.dsl.SsmChaincodeD2Model]
 * @title SSM-CHAINCODE/InvokeReturn
 */
@Serializable
@JsExport
@JsName("SsmCommandResult")
data class SsmCommandResult(
	/**
	 * Identifier of the transaction
	 * @example [ssm.chaincode.dsl.blockchain.Transaction.transactionId]
	 */
	override val transactionId: TransactionId,
): SsmCommandResultDTO
