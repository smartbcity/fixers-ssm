package ssm.chaincode.dsl.blockchain

import kotlinx.serialization.Serializable
import kotlin.js.JsExport
import kotlin.js.JsName

typealias BlockId = Long

expect interface BlockDTO {
    val blockId: BlockId
    val previousHash: ByteArray
    val dataHash: ByteArray
    val transactions: List<TransactionDTO>
}

@Serializable
@JsExport
@JsName("Block")
class Block(
    override val blockId: BlockId,
    override val previousHash: ByteArray,
    override val dataHash: ByteArray,
    override val transactions: List<Transaction>
): BlockDTO

