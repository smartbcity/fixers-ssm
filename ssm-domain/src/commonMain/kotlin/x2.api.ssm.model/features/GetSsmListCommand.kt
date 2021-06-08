package x2.api.ssm.model.features

import f2.dsl.function.F2Function
import f2.dsl.function.F2FunctionRemote
import kotlinx.serialization.Serializable
import x2.api.ssm.model.Ssm
import kotlin.js.JsExport
import kotlin.js.JsName


typealias GetSsmListQueryFunction = F2Function<GetSsmListCommand, List<Ssm>>
typealias GetSsmListQueryRemoteFunction = F2FunctionRemote<GetSsmListCommand, Array<Ssm>>

@JsExport
@JsName("GetSsmListCommand")
interface GetSsmListCommand {
    val dbName: String
}

@JsExport
@JsName("GetSsmListCommandBase")
class GetSsmListCommandBase(
    override val dbName: String
): GetSsmListCommand
