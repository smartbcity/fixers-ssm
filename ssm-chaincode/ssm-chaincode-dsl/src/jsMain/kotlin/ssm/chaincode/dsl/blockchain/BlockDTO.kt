package ssm.chaincode.dsl.blockchain

@JsExport
@JsName("BlockDTO")
actual external interface BlockDTO {
	actual val blockId: BlockId
	actual val previousHash: ByteArray
	actual val dataHash: ByteArray
	actual val transactions: List<TransactionDTO>
}
