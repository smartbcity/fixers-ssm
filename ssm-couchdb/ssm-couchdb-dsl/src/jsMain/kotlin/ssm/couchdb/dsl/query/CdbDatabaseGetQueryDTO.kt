package ssm.couchdb.dsl.query

import ssm.couchdb.dsl.CdbQueryDTO
import ssm.couchdb.dsl.DatabaseDTO

@JsExport
@JsName("CdbDatabaseGetQueryDTO")
actual external interface CdbDatabaseGetQueryDTO : CdbQueryDTO {
	actual val dbName: String
	actual override val dbConfig: String
}

@JsExport
@JsName("CdbDatabaseGetQueryResultDTO")
actual external interface CdbDatabaseGetQueryResultDTO {
	actual val item: DatabaseDTO
}
