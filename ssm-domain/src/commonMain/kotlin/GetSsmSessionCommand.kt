import f2.dsl.function.F2Function
import f2.dsl.function.F2FunctionRemote
import kotlin.js.JsExport
import kotlin.js.JsName

typealias GetSsmSessionQueryFunction = F2Function<GetSsmSessionCommand, TxSsmSessionBase?>
typealias GetSsmSessionQueryRemoteFunction = F2FunctionRemote<GetSsmSessionCommand, TxSsmSession?>


@JsExport
@JsName("GetSsmSessionCommand")
interface GetSsmSessionCommand: TxSsmCommand {
    val sessionId: String
    override val ssm: SsmName
    override val bearerToken: String?
}

@JsExport
@JsName("GetSsmSessionCommandBase")
class GetSsmSessionCommandBase(
    override val sessionId: String,
    override val ssm: SsmName,
    override val bearerToken: String?
): GetSsmSessionCommand
