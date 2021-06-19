import ssm.chaincode.dsl.blockchain.TransactionId
import kotlin.js.JsExport
import kotlin.js.JsName

@JsExport
@JsName("GetSsmSessionLogCommand")
interface GetSsmSessionLogCommand: TxSsmCommand {
    val sessionId: TxSsmSessionId
    val txId: TransactionId
    override val ssm: SsmName
    override val bearerToken: String?
}

@JsExport
@JsName("GetSsmSessionLogCommandBase")
class GetSsmSessionLogCommandBase(
    override val sessionId: TxSsmSessionId,
    override val txId: TransactionId,
    override val ssm: SsmName,
    override val bearerToken: String?
): GetSsmSessionLogCommand
