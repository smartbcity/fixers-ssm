package ssm.couchdb.dsl.query

import f2.dsl.function.F2Function
import f2.dsl.function.F2FunctionRemote
import ssm.chaincode.dsl.SsmSessionStateBase
import ssm.couchdb.dsl.CdbQuery
import kotlin.js.JsExport
import kotlin.js.JsName

typealias CdbSsmSessionListQueryFunction = F2Function<CdbSsmSessionListQueryDTO, CdbSsmSessionListQueryResultDTO>
typealias CdbSsmSessionListQueryFunctionRemote = F2FunctionRemote<CdbSsmSessionListQueryDTO, CdbSsmSessionListQueryResultDTO>

@JsExport
@JsName("CdbSsmSessionListQueryDTO")
interface CdbSsmSessionListQueryDTO: CdbQuery{
    val dbName: String
    val ssm: String?
    override val dbConfig: String
}

@JsExport
@JsName("CdbSsmSessionListQuery")
class CdbSsmSessionListQuery(
    override val dbName: String,
    override val ssm: String?,
    override val dbConfig: String
): CdbSsmSessionListQueryDTO

@JsExport
@JsName("CdbSsmSessionListQueryResultDTO")
interface CdbSsmSessionListQueryResultDTO{
    val sessions: Array<SsmSessionStateBase>
}

@JsExport
@JsName("CdbSsmSessionListQueryResult")
class CdbSsmSessionListQueryResult(
    override val sessions: Array<SsmSessionStateBase>
): CdbSsmSessionListQueryResultDTO