package x2.api.ssm.model.features

import kotlin.js.JsExport
import kotlin.js.JsName

@JsExport
@JsName("GetSsmSessionListCommmand")
interface GetSsmSessionListCommand {
    val dbName: String
    val ssm: String?
}

@JsExport
@JsName("GetSsmSessionListCommmandBase")
class GetSsmSessionListCommandBase(
    override val dbName: String,
    override val ssm: String?
): GetSsmSessionListCommand
