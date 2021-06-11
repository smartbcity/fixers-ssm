import ssm.dsl.SsmCommand
import ssm.dsl.blockchain.TransactionId
import kotlin.js.JsExport
import kotlin.js.JsName

@JsExport
@JsName("GetSsmSessionLogCommand")
interface GetSsmSessionLogCommand: SsmCommand {
    val sessionId: TxSsmSessionId
    val txId: TransactionId
    override val baseUrl: String
    override val channelId: String?
    override val chaincodeId: String?
    override val bearerToken: String?
}

@JsExport
@JsName("GetSsmSessionLogCommandBase")
class GetSsmSessionLogCommandBase(
    override val sessionId: TxSsmSessionId,
    override val txId: TransactionId,
    override val baseUrl: String,
    override val channelId: String?,
    override val chaincodeId: String?,
    override val bearerToken: String?
): GetSsmSessionLogCommand
