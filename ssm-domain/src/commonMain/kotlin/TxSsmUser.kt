import kotlin.js.JsExport
import kotlin.js.JsName

@JsExport
@JsName("TxSsmUser")
interface TxSsmUser {
    val name: String
    val publicKey: String
}

@JsExport
@JsName("TxSsmUserBase")
class TxSsmUserBase(
    override val name: String,
    override val publicKey: String
): TxSsmUser
