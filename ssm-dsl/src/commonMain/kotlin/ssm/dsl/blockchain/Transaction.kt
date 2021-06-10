package ssm.dsl.blockchain

import kotlin.js.JsExport
import kotlin.js.JsName

@JsExport
@JsName("Transaction")
interface Transaction {
    val transactionId: TransactionId
    val blockId: Long
    val timestamp: Long
    val isValid: Boolean
    val channelId: String
    val creator: IdentitiesInfo
    val nonce: ByteArray
    val type: EnvelopeType
    val validationCode: Byte
}

@JsExport
@JsName("TransactionBase")
class TransactionBase(
    override val transactionId: TransactionId,
    override val blockId: Long,
    override val timestamp: Long,
    override val isValid: Boolean,
    override val channelId: String,
    override val creator: IdentitiesInfoBase,
    override val nonce: ByteArray,
    override val type: EnvelopeType,
    override val validationCode: Byte
): Transaction

typealias TransactionId = String
