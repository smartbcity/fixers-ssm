package ssm.couchdb.dsl.query

import f2.dsl.fnc.F2Function
import kotlin.js.JsExport
import kotlin.js.JsName
import kotlinx.serialization.Serializable
import ssm.couchdb.dsl.CdbQueryDTO
import ssm.couchdb.dsl.Database
import ssm.couchdb.dsl.DatabaseDTO

typealias CdbDatabaseGetQueryFunction = F2Function<CdbDatabaseGetQueryDTO, CdbDatabaseGetQueryResultDTO>

expect interface CdbDatabaseGetQueryDTO : CdbQueryDTO {
	val dbName: String
	override val dbConfig: String
}

@Serializable
@JsExport
@JsName("CdbDatabaseGetQuery")
class CdbDatabaseGetQuery(
	override val dbName: String,
	override val dbConfig: String,
) : CdbDatabaseGetQueryDTO

expect interface CdbDatabaseGetQueryResultDTO {
	val item: DatabaseDTO
}

@Serializable
@JsExport
@JsName("CdbSsmDatabaseGetQueryResult")
class CdbSsmDatabaseGetQueryResult(
	override val item: Database,
) : CdbDatabaseGetQueryResultDTO
