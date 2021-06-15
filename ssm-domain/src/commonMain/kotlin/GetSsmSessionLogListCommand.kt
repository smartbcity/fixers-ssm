
import kotlin.js.JsExport
import kotlin.js.JsName

@JsExport
@JsName("GetSsmSessionLogListCommand")
interface GetSsmSessionLogListCommand: TxSsmCommand {
    val sessionId: TxSsmSessionId
    override val ssm: SsmName
    override val bearerToken: String?
}

@JsExport
@JsName("GetSsmSessionLogListBaseCommand")
class GetSsmSessionLogListCommandBase(
    override val sessionId: TxSsmSessionId,
    override val ssm: SsmName,
    override val bearerToken: String?
): GetSsmSessionLogListCommand