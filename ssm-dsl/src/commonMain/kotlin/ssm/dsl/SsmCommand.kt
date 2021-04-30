package ssm.dsl

import f2.dsl.cqrs.Command
import kotlin.js.JsExport
import kotlin.js.JsName

@JsExport
@JsName("SsmCommand")
interface SsmCommand: Command {
    val baseUrl: String
    val channelId: String?
    val chaincodeId: String?
    val bearerToken: String?
}
