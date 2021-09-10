package ssm.couchdb.dsl.query

import ssm.couchdb.dsl.CdbQueryDTO
import ssm.couchdb.dsl.DatabaseDTO

@JsExport
@JsName("CdbDatabaseListQueryDTO")
actual external interface CdbDatabaseListQueryDTO : CdbQueryDTO

@JsExport
@JsName("CdbDatabaseListQueryResultDTO")
actual external interface CdbDatabaseListQueryResultDTO {
	actual val list: List<DatabaseDTO>
}
