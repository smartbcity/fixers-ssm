package ssm.couchdb.dsl

import f2.dsl.cqrs.Command
import kotlin.js.JsExport
import kotlin.js.JsName

@JsExport
@JsName("CdbCommand")
interface CdbCommand: Command {
    val dbConfig: String
}
