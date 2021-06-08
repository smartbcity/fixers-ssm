package x2.api.ssm.domain.model

import kotlin.js.JsExport
import kotlin.js.JsName

@JsExport
@JsName("SsmTransaction")
interface SsmTransaction {
    val id: SsmTransactionId
    val from: Int
    val to: Int
    val date: Long
    val signer: SsmUser
}

@JsExport
@JsName("SsmTransactionBase")
class SsmTransactionBase(
    override val id: SsmTransactionId,
    override val from: Int,
    override val to: Int,
    override val date: Long,
    override val signer: SsmUserBase
): SsmTransaction

typealias SsmTransactionId = String
