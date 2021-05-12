package ssm.dsl.blockchain

typealias BlockId = Long

class Block(
	val blockId: BlockId,
	val previousHash: ByteArray,
	val dataHash: ByteArray,
	val transactions: List<Transaction>
)