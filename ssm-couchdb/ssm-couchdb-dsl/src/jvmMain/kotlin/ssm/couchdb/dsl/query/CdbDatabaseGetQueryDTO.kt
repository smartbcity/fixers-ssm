package ssm.couchdb.dsl.query

import ssm.couchdb.dsl.CdbQueryDTO
import ssm.couchdb.dsl.DatabaseDTO

actual interface CdbDatabaseGetQueryDTO : CdbQueryDTO {
	actual val dbName: String
	actual override val dbConfig: String
}

actual interface CdbDatabaseGetQueryResultDTO {
	actual val item: DatabaseDTO
}