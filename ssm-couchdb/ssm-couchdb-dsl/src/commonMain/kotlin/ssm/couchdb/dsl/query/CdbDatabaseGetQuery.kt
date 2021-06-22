package ssm.couchdb.dsl.query

import f2.dsl.function.F2Function
import f2.dsl.function.F2FunctionRemote
import ssm.couchdb.dsl.CdbQuery
import ssm.couchdb.dsl.Database
import ssm.couchdb.dsl.DatabaseDTO
import ssm.couchdb.dsl.DatabaseName
import kotlin.js.JsExport
import kotlin.js.JsName

typealias CdbDatabaseGetQueryFunction = F2Function<CdbDatabaseGetQueryDTO, CdbDatabaseGetQueryResultDTO>
typealias CdbDatabaseGetQueryFunctionRemote = F2FunctionRemote<CdbDatabaseGetQueryDTO, CdbDatabaseGetQueryResultDTO>


@JsExport
@JsName("CdbDatabaseGetQueryDTO")
interface CdbDatabaseGetQueryDTO : CdbQuery {
	val dbName: String
	override val dbConfig: String
}

@JsExport
@JsName("CdbDatabaseGetQuery")
class CdbDatabaseGetQuery(
    override val dbName: String,
    override val dbConfig: String,
) : CdbDatabaseGetQueryDTO


@JsExport
@JsName("CdbDatabaseGetQueryResultDTO")
interface CdbDatabaseGetQueryResultDTO {
	val item: DatabaseDTO
}

@JsExport
@JsName("CdbSsmDatabaseGetQueryResult")
class CdbSsmDatabaseGetQueryResult(
    override val item: Database,
) : CdbDatabaseGetQueryResultDTO