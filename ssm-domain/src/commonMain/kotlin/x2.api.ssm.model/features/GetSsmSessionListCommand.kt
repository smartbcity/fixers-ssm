package x2.api.ssm.model.features

import kotlin.js.JsExport
import kotlin.js.JsName

@JsExport
@JsName("GetSsmSessionListCommand")
interface GetSsmSessionListCommand {
    val baseUrl: String
    val dbName: String
    val channelId: String?
    val chaincodeId: String?
    val bearerToken: String?
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
