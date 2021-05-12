package ssm.dsl.blockchain

typealias TransactionId = String
class Transaction(
	val transactionId: TransactionId,
	val blockId: Long,
	val timestamp: Long,
	val isValid: Boolean,
	val channelId: String,
	val creator: IdentitiesInfo,
	val nonce: ByteArray,
	val type: EnvelopeType,
	val validationCode: Byte,
)

class IdentitiesInfo(
	val mspid: String,
	val id: String,
)

enum class EnvelopeType {
	TRANSACTION_ENVELOPE, ENVELOPE
}