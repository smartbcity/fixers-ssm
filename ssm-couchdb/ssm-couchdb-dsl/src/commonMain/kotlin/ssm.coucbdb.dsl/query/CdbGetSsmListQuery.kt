package ssm.coucbdb.dsl.query

import f2.dsl.function.F2Function
import f2.dsl.function.F2FunctionRemote
import ssm.dsl.SsmBase
import kotlin.js.JsExport
import kotlin.js.JsName

typealias CdbGetSsmListQueryFunction = F2Function<CdbGetSsmListQuery, CdbGetSsmListQueryResult>
typealias CdbGetSsmListQueryFunctionRemote = F2FunctionRemote<CdbGetSsmListQuery, CdbGetSsmListQueryResult>

@JsExport
@JsName("CdbGetSsmListQuery")
class CdbGetSsmListQuery(
    val dbName: String
)

@JsExport
@JsName("CdbGetSsmListQueryResult")
class CdbGetSsmListQueryResult(
    val ssmList: List<SsmBase>
)