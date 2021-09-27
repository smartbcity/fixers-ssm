package ssm.tx.dsl.model

import kotlin.js.JsExport
import kotlin.js.JsName
import kotlinx.serialization.Serializable
import ssm.chaincode.dsl.model.SsmSessionState
import ssm.chaincode.dsl.model.SsmSessionStateDTO
import ssm.chaincode.dsl.blockchain.Transaction
import ssm.chaincode.dsl.blockchain.TransactionDTO

expect interface TxSsmSessionStateDTO {
	/**
	 * The state of an SSM session
	 */
	val details: SsmSessionStateDTO

	/**
	 * Transaction that initiated the state of the session
	 */
	val transaction: TransactionDTO?
}

/**
 * @d2 model
 * @parent [TxSsmSession]
 */
@Serializable
@JsExport
@JsName("TxSsmSessionState")
class TxSsmSessionState(
	override val details: SsmSessionState,
	override val transaction: Transaction?,
) : TxSsmSessionStateDTO
