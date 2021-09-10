package ssm.couchdb.dsl.query

import f2.dsl.fnc.F2Function
import kotlin.js.JsExport
import kotlin.js.JsName
import kotlinx.serialization.Serializable
import ssm.couchdb.dsl.CdbQueryDTO
import ssm.couchdb.dsl.Database
import ssm.couchdb.dsl.DatabaseDTO

typealias CdbDatabaseListQueryFunction = F2Function<CdbDatabaseListQueryDTO, CdbDatabaseListQueryResultDTO>

expect interface CdbDatabaseListQueryDTO : CdbQueryDTO

@Serializable
@JsExport
@JsName("CdbDatabaseListQuery")
class CdbDatabaseListQuery(
    override val dbConfig: String,
) : CdbDatabaseListQueryDTO

expect interface CdbDatabaseListQueryResultDTO {
	val list: List<DatabaseDTO>
}

@Serializable
@JsExport
@JsName("CdbDatabaseListQueryResult")
class CdbDatabaseListQueryResult(
    override val list: List<Database>,
) : CdbDatabaseListQueryResultDTO
