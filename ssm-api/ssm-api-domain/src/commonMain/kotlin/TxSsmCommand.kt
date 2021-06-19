import kotlin.js.JsExport
import kotlin.js.JsName

@JsExport
@JsName("TxSsmCommand")
interface TxSsmCommand {
    val ssm: SsmName
    val bearerToken: String?
}
