package ssm.chaincode.dsl.blockchain

import kotlin.js.JsExport
import kotlin.js.JsName

@JsExport
@JsName("Block")
interface Block {
    val blockId: BlockId
    val previousHash: ByteArray
    val dataHash: ByteArray
    val transactions: List<Transaction>
}

@JsExport
@JsName("BlockBase")
class BlockBase(
    override val blockId: BlockId,
    override val previousHash: ByteArray,
    override val dataHash: ByteArray,
    override val transactions: List<TransactionBase>
): Block

typealias BlockId = Long
