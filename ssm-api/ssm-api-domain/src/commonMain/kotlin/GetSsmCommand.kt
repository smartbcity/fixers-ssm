import kotlin.js.JsExport
import kotlin.js.JsName

@JsExport
@JsName("GetSsmCommand")
interface GetSsmCommand: TxSsmCommand {
    override val ssm: SsmName
    override val bearerToken: String?
}

@JsExport
@JsName("GetSsmCommandBase")
class GetSsmCommandBase(
    override val ssm: SsmName,
    override val bearerToken: String?
): GetSsmCommand
