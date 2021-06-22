package ssm.couchdb.dsl

import f2.dsl.cqrs.Command
import f2.dsl.cqrs.Query
import kotlin.js.JsExport
import kotlin.js.JsName

@JsExport
@JsName("CdbCommand")
interface CdbQuery: Query {
    val dbConfig: String
}
