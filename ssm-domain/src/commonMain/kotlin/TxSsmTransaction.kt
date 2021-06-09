import kotlin.js.JsExport
import kotlin.js.JsName

@JsExport
@JsName("TxSsmTransaction")
interface TxSsmTransaction {
    val id: TxSsmTransactionId
    val from: Int
    val to: Int
    val date: Long
    val signer: TxSsmUser
}

@JsExport
@JsName("TxSsmTransactionBase")
class TxSsmTransactionBase(
    override val id: TxSsmTransactionId,
    override val from: Int,
    override val to: Int,
    override val date: Long,
    override val signer: TxSsmUserBase
): TxSsmTransaction

typealias TxSsmTransactionId = String
