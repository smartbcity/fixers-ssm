package ssm.chaincode.dsl.blockchain

expect interface TransactionDTO {
	/**
	 * Identifier of the transaction
	 * @example "c7de3ab6-56e0-4e7d-8fa4-905823ed982e"
	 */
	val transactionId: TransactionId

	/**
	 * [Block] holding the transaction within the blockchain
	 * @example [Block.blockId]
	 */
	val blockId: Long

	/**
	 * Execution date of the transaction
	 * @example 1627984925000
	 */
	val timestamp: Long

	/**
	 * Indicates if the transaction has been validated or not
	 * @example true
	 */
	val isValid: Boolean

	/**
	 * Channel in which the transaction has been performed
	 * @example "channel-smartb"
	 */
	val channelId: String

	/**
	 * Requester of the transaction
	 */
	val creator: IdentitiesInfoDTO

	/**
	 * TODO
	 * @example "TODO"
	 */
	val nonce: ByteArray

	/**
	 * TODO
	 * @example "TODO"
	 */
	val type: EnvelopeType

	/**
	 * TODO
	 * @example "TODO"
	 */
	val validationCode: Byte
}

/**
 * @d2 model
 * @page
 * @@title SSM-CHAINCODE/Blockchain Content
 */
class Transaction(
	override val transactionId: TransactionId,
	override val blockId: Long,
	override val timestamp: Long,
	override val isValid: Boolean,
	override val channelId: String,
	override val creator: IdentitiesInfo,
	override val nonce: ByteArray,
	override val type: EnvelopeType,
	override val validationCode: Byte,
) : TransactionDTO

typealias TransactionId = String
