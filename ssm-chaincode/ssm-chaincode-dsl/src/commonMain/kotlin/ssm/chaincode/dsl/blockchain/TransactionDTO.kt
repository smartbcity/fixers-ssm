package ssm.chaincode.dsl.blockchain

import kotlinx.serialization.Serializable
import kotlin.js.JsExport
import kotlin.js.JsName

expect interface TransactionDTO {
    val transactionId: TransactionId
    val blockId: Long
    val timestamp: Long
    val isValid: Boolean
    val channelId: String
    val creator: IdentitiesInfoDTO
    val nonce: ByteArray
    val type: EnvelopeType
    val validationCode: Byte
}

/**
 * @d2 model
 */
@Serializable
@JsExport
@JsName("Transaction")
class Transaction(
    /**
     * @example "c7de3ab6-56e0-4e7d-8fa4-905823ed982e"
     */
    override val transactionId: TransactionId,
    override val blockId: Long,
    override val timestamp: Long,
    override val isValid: Boolean,
    override val channelId: String,
    override val creator: IdentitiesInfo,
    override val nonce: ByteArray,
    override val type: EnvelopeType,
    override val validationCode: Byte
): TransactionDTO

typealias TransactionId = String
