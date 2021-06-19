import f2.dsl.function.F2Function
import f2.dsl.function.F2FunctionRemote
import kotlin.js.JsExport
import kotlin.js.JsName

typealias GetSsmSessionListQueryFunction = F2Function<GetSsmSessionListCommand,  List<TxSsmSessionBase>>
typealias GetSsmSessionListQueryRemoteFunction = F2FunctionRemote<GetSsmSessionListCommand, Array<TxSsmSession>>


@JsExport
@JsName("GetSsmSessionListCommand")
interface GetSsmSessionListCommand: TxSsmCommand {
    override val ssm: String
    override val bearerToken: String?
}

@JsExport
@JsName("GetSsmSessionListCommandBase")
class GetSsmSessionListCommandBase(
    override val ssm: String,
    override val bearerToken: String?
): GetSsmSessionListCommand
