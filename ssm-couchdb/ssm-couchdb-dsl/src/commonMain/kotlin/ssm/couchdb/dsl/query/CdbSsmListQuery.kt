package ssm.couchdb.dsl.query

import f2.dsl.function.F2Function
import f2.dsl.function.F2FunctionRemote
import ssm.chaincode.dsl.SsmBase
import ssm.couchdb.dsl.CdbQuery
import kotlin.js.JsExport
import kotlin.js.JsName

typealias CdbSsmListQueryFunction = F2Function<CdbSsmListQueryDTO, CdbSsmListQueryResultDTO>
typealias CdbSsmListQueryFunctionRemote = F2FunctionRemote<CdbSsmListQueryDTO, CdbSsmListQueryResultDTO>

@JsExport
@JsName("CdbSsmListQueryDTO")
interface CdbSsmListQueryDTO: CdbQuery {
    val dbName: String
    override val dbConfig: String
}

@JsExport
@JsName("CdbSsmListQuery")
class CdbSsmListQuery(
    override val dbName: String,
    override val dbConfig: String,
): CdbSsmListQueryDTO

@JsExport
@JsName("CdbSsmListQueryResultDTO")
interface CdbSsmListQueryResultDTO{
    val ssmList: List<SsmBase>
}

@JsExport
@JsName("CdbSsmListQueryResult")
class CdbSsmListQueryResult(
    override val ssmList: List<SsmBase>
): CdbSsmListQueryResultDTO