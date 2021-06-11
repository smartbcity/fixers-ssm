
import ssm.dsl.SsmCommand
import kotlin.js.JsExport
import kotlin.js.JsName

@JsExport
@JsName("GetSsmSessionLogListCommand")
interface GetSsmSessionLogListCommand: SsmCommand {
    val sessionId: TxSsmSessionId
    override val baseUrl: String
    override val channelId: String?
    override val chaincodeId: String?
    override val bearerToken: String?
}

@JsExport
@JsName("GetSsmSessionLogListBaseCommand")
class GetSsmSessionLogListCommandBase(
    override val sessionId: TxSsmSessionId,
    override val baseUrl: String,
    override val channelId: String?,
    override val chaincodeId: String?,
    override val bearerToken: String?
): GetSsmSessionLogListCommand