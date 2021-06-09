package x2.api.ssm.domain.model

import kotlin.js.JsExport
import kotlin.js.JsName

@JsExport
@JsName("SsmUser")
interface TxSsmUser {
    val name: String
    val publicKey: String
}

@JsExport
@JsName("SsmUserBase")
class TxSsmUserBase(
    override val name: String,
    override val publicKey: String
): TxSsmUser
