package ssm.couchdb.dsl.query

import f2.dsl.function.F2Function
import f2.dsl.function.F2FunctionRemote
import ssm.couchdb.dsl.CdbQuery
import ssm.couchdb.dsl.Database
import ssm.couchdb.dsl.DatabaseDTO
import kotlin.js.JsExport
import kotlin.js.JsName

typealias CdbDatabaseListQueryFunction = F2Function<CdbDatabaseListQueryDTO, CdbDatabaseListQueryResultDTO>
typealias CdbDatabaseListQueryRemoteFunction = F2FunctionRemote<CdbDatabaseListQueryDTO, CdbDatabaseListQueryResultDTO>


@JsExport
@JsName("CdbDatabaseListQueryDTO")
interface CdbDatabaseListQueryDTO: CdbQuery {
}

@JsExport
@JsName("CdbDatabaseListQuery")
class CdbDatabaseListQuery(
    override val dbConfig: String
): CdbDatabaseListQueryDTO

@JsExport
@JsName("CdbDatabaseListQueryResultDTO")
interface CdbDatabaseListQueryResultDTO {
    val list: List<DatabaseDTO>
}

@JsExport
@JsName("CdbDatabaseListQueryResult")
class CdbDatabaseListQueryResult(
    override val list: List<Database>
): CdbDatabaseListQueryResultDTO