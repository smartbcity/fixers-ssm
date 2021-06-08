package x2.api.ssm.domain.features

import x2.api.ssm.domain.model.TxSsm
import f2.dsl.function.F2Function
import f2.dsl.function.F2FunctionRemote
import kotlin.js.JsExport
import kotlin.js.JsName


typealias GetSsmListQueryFunction = F2Function<GetSsmListCommand, List<TxSsm>>
typealias GetSsmListQueryRemoteFunction = F2FunctionRemote<GetSsmListCommand, Array<TxSsm>>

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
