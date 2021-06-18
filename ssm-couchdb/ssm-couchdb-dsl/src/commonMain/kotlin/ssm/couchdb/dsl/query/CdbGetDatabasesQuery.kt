package ssm.couchdb.dsl.query

import ssm.couchdb.dsl.CdbCommand
import kotlin.js.JsExport
import kotlin.js.JsName

@JsExport
@JsName("CdbGetDatabasesQuery")
class CdbGetDatabasesQuery(
    override val dbConfig: String
): CdbCommand
