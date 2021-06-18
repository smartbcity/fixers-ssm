package ssm.couchdb.dsl.query

import f2.dsl.function.F2Function
import f2.dsl.function.F2FunctionRemote
import ssm.chaincode.dsl.SsmBase
import ssm.couchdb.dsl.CdbCommand
import kotlin.js.JsExport
import kotlin.js.JsName

typealias CdbGetSsmListQueryFunction = F2Function<CdbGetSsmListQuery, CdbGetSsmListQueryResult>
typealias CdbGetSsmListQueryFunctionRemote = F2FunctionRemote<CdbGetSsmListQuery, CdbGetSsmListQueryResult>

@JsExport
@JsName("CdbGetSsmListQuery")
class CdbGetSsmListQuery(
    val dbName: String,
    override val dbConfig: String,
): CdbCommand

@JsExport
@JsName("CdbGetSsmListQueryResult")
class CdbGetSsmListQueryResult(
    val ssmList: List<SsmBase>
)