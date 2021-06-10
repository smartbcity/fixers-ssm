
import ssm.dsl.SsmCommand
import kotlin.js.JsExport
import kotlin.js.JsName

@JsExport
@JsName("GetSsmSessionLogsCommand")
interface GetSsmSessionLogsCommand: SsmCommand {
    val sessionId: String
    override val baseUrl: String
    override val channelId: String?
    override val chaincodeId: String?
    override val bearerToken: String?
}

@JsExport
@JsName("GetSsmSessionLogsBaseCommand")
class GetSsmSessionLogsCommandBase(
    override val sessionId: String,
    override val baseUrl: String,
    override val channelId: String?,
    override val chaincodeId: String?,
    override val bearerToken: String?
): GetSsmSessionLogsCommand