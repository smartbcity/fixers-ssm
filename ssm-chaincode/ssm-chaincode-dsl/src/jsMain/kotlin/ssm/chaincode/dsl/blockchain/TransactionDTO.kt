package ssm.chaincode.dsl.blockchain

@JsExport
@JsName("TransactionDTO")
actual external interface TransactionDTO {
	actual val transactionId: TransactionId
	actual val blockId: Long
	actual val timestamp: Long
	actual val isValid: Boolean
	actual val channelId: String
	actual val creator: IdentitiesInfoDTO
	actual val nonce: ByteArray
	actual val type: EnvelopeType
	actual val validationCode: Byte
}
