package ssm.chaincode.dsl

import f2.dsl.cqrs.Command
import kotlin.js.JsExport
import kotlin.js.JsName

expect interface SsmCommandDTO: Command {
    val baseUrl: String
    val channelId: String?
    val chaincodeId: String?
    val bearerToken: String?
}
