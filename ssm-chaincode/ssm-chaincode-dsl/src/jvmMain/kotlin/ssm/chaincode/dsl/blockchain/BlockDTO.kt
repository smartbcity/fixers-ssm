package ssm.chaincode.dsl.blockchain

actual interface BlockDTO {
	actual val blockId: BlockId
	actual val previousHash: ByteArray
	actual val dataHash: ByteArray
	actual val transactions: List<TransactionDTO>
}