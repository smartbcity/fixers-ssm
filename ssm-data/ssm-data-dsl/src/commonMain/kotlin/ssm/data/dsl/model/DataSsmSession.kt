package ssm.data.dsl.model

import kotlin.js.JsExport
import kotlin.js.JsName
import kotlinx.serialization.Serializable
import ssm.chaincode.dsl.blockchain.Transaction
import ssm.chaincode.dsl.blockchain.TransactionDTO

expect interface DataSsmSessionDTO {
	/**
	 * Identifier of the session
	 * @example [ssm.chaincode.dsl.model.SsmSession.session]
	 */
	val id: DataSsmSessionId

	/**
	 * The state of an SSM session
	 */
	val state: DataSsmSessionStateDTO

	/**
	 * The channel this session was instantiated in
	 */
	val channel: DataChannelDTO

	/**
	 * Transaction that initiated the state of the session
	 */
	val transaction: TransactionDTO?
}

/**
 * @d2 model
 * @page
 * Represents an SSM session state with some metadata
 * @@title SSM-TX/Session
 */
@Serializable
@JsExport
@JsName("DataSsmSession")
class DataSsmSession(
	override val id: DataSsmSessionId,
	override val state: DataSsmSessionState,
	override val channel: TxChannel,
	override val transaction: Transaction?,
) : DataSsmSessionDTO

typealias DataSsmSessionId = String
