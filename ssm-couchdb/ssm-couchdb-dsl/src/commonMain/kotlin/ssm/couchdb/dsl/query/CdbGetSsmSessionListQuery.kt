package ssm.couchdb.dsl.query

import f2.dsl.function.F2Function
import f2.dsl.function.F2FunctionRemote
import ssm.couchdb.dsl.CdbCommand
import ssm.dsl.SsmSessionStateBase
import kotlin.js.JsExport
import kotlin.js.JsName

typealias CdbGetSsmSessionListQueryFunction = F2Function<CdbGetSsmSessionListQuery, CdbGetSsmSessionListQueryResult>
typealias CdbGetSsmSessionListQueryFunctionRemote = F2FunctionRemote<CdbGetSsmSessionListQuery, CdbGetSsmSessionListQueryResult>

@JsExport
@JsName("CdbGetSsmSessionListQuery")
class CdbGetSsmSessionListQuery(
    val dbName: String,
    val ssm: String?,
    override val dbConfig: String
): CdbCommand

@JsExport
@JsName("CdbGetSsmSessionListQueryResult")
class CdbGetSsmSessionListQueryResult(
    val sessions: Array<SsmSessionStateBase>
)