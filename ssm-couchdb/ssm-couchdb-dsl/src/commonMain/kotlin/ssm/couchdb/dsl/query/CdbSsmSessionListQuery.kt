package ssm.couchdb.dsl.query

import f2.dsl.fnc.F2Function
import kotlinx.serialization.Serializable
import ssm.chaincode.dsl.SsmSessionStateBase
import ssm.couchdb.dsl.CdbQueryDTO
import kotlin.js.JsExport
import kotlin.js.JsName

typealias CdbSsmSessionListQueryFunction = F2Function<CdbSsmSessionListQueryDTO, CdbSsmSessionListQueryResultDTO>

expect interface CdbSsmSessionListQueryDTO: CdbQueryDTO{
    val dbName: String
    val ssm: String?
    override val dbConfig: String
}

@Serializable
@JsExport
@JsName("CdbSsmSessionListQuery")
class CdbSsmSessionListQuery(
    override val dbName: String,
    override val ssm: String?,
    override val dbConfig: String
): CdbSsmSessionListQueryDTO

expect interface CdbSsmSessionListQueryResultDTO{
    val sessions: Array<SsmSessionStateBase>
}

@Serializable
@JsExport
@JsName("CdbSsmSessionListQueryResult")
class CdbSsmSessionListQueryResult(
    override val sessions: Array<SsmSessionStateBase>
): CdbSsmSessionListQueryResultDTO