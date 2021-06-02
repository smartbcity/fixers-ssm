package x2.api.ssm.model.features

import ssm.dsl.SsmCommand
import kotlin.js.JsExport
import kotlin.js.JsName

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
