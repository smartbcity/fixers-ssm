package ssm.coucbdb.dsl.query

import f2.dsl.function.F2Function
import f2.dsl.function.F2FunctionRemote
import ssm.dsl.SsmSessionState
import ssm.dsl.SsmSessionStateBase
import kotlin.js.JsExport
import kotlin.js.JsName

typealias CdbGetSsmSessionListQueryFunction = F2Function<CdbGetSsmSessionListQuery, CdbGetSsmSessionListQueryResult>
typealias CdbGetSsmSessionListQueryFunctionRemote = F2FunctionRemote<CdbGetSsmSessionListQuery, CdbGetSsmSessionListQueryResult>

@JsExport
@JsName("CdbGetSsmSessionListQuery")
class CdbGetSsmSessionListQuery(
    val dbName: String,
    val ssm: String?
)

@JsExport
@JsName("CdbGetSsmSessionListQueryResult")
class CdbGetSsmSessionListQueryResult(
    val sessions: List<SsmSessionStateBase>
)