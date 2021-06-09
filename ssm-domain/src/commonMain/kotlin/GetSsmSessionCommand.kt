import f2.dsl.function.F2Function
import f2.dsl.function.F2FunctionRemote
import ssm.dsl.SsmCommand
import kotlin.js.JsExport
import kotlin.js.JsName

typealias GetSsmSessionQueryFunction = F2Function<GetSsmSessionCommand,  TxSsmSessionBase?>
typealias GetSsmSessionQueryRemoteFunction = F2FunctionRemote<GetSsmSessionCommand, TxSsmSession?>


@JsExport
@JsName("GetSsmSessionCommand")
interface GetSsmSessionCommand: SsmCommand {
    val name: String
    override val baseUrl: String
    override val channelId: String?
    override val chaincodeId: String?
    override val bearerToken: String?
}

@JsExport
@JsName("GetSsmSessionCommandBase")
class GetSsmSessionCommandBase(
    override val name: String,
    override val baseUrl: String,
    override val channelId: String?,
    override val chaincodeId: String?,
    override val bearerToken: String?
): GetSsmSessionCommand
