package x2.api.ssm.model.features

import kotlin.js.JsExport
import kotlin.js.JsName

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
