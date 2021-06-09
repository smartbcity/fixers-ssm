import f2.dsl.function.F2Function
import f2.dsl.function.F2FunctionRemote
import ssm.dsl.SsmCommand
import kotlin.js.JsExport
import kotlin.js.JsName

typealias GetSsmSessionListQueryFunction = F2Function<GetSsmSessionListCommand,  List<TxSsmSessionBase>>
typealias GetSsmSessionListQueryRemoteFunction = F2FunctionRemote<GetSsmSessionListCommand, Array<TxSsmSession>>


@JsExport
@JsName("GetSsmSessionListCommand")
interface GetSsmSessionListCommand: SsmCommand {
    override val baseUrl: String
    override val channelId: String?
    override val chaincodeId: String?
    override val bearerToken: String?
    val dbName: String
    val ssm: String?
}

@JsExport
@JsName("GetSsmSessionListCommandBase")
class GetSsmSessionListCommandBase(
    override val baseUrl: String,
    override val dbName: String,
    override val channelId: String?,
    override val chaincodeId: String?,
    override val bearerToken: String?,
    override val ssm: String?
): GetSsmSessionListCommand
