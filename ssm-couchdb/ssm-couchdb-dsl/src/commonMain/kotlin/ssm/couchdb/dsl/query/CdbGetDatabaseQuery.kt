package ssm.couchdb.dsl.query

import ssm.couchdb.dsl.CdbCommand
import kotlin.js.JsExport
import kotlin.js.JsName

@JsExport
@JsName("CdbGetDatabaseQuery")
class CdbGetDatabaseQuery(
    val dbName: String,
    override val dbConfig: String
): CdbCommand
