package ssm.couchdb.dsl

import f2.dsl.cqrs.Query

@JsExport
@JsName("CdbQueryDTO")
actual external interface CdbQueryDTO : Query {
	actual val dbConfig: String
}
