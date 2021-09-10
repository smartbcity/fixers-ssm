package ssm.couchdb.dsl.query

import ssm.couchdb.dsl.CdbQueryDTO
import ssm.couchdb.dsl.DatabaseDTO

actual interface CdbDatabaseListQueryDTO : CdbQueryDTO

actual interface CdbDatabaseListQueryResultDTO {
	actual val list: List<DatabaseDTO>
}
