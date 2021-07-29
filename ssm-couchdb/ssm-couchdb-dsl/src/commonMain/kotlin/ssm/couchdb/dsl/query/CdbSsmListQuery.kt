package ssm.couchdb.dsl.query

import f2.dsl.fnc.F2Function
import kotlinx.serialization.Serializable
import ssm.chaincode.dsl.Ssm
import ssm.couchdb.dsl.CdbQueryDTO
import kotlin.js.JsExport
import kotlin.js.JsName

typealias CdbSsmListQueryFunction = F2Function<CdbSsmListQueryDTO, CdbSsmListQueryResultDTO>

expect interface CdbSsmListQueryDTO: CdbQueryDTO {
    val dbName: String
    override val dbConfig: String
}

@Serializable
@JsExport
@JsName("CdbSsmListQuery")
class CdbSsmListQuery(
    override val dbName: String,
    override val dbConfig: String,
): CdbSsmListQueryDTO

expect interface CdbSsmListQueryResultDTO{
    val ssmList: List<Ssm>
}

@Serializable
@JsExport
@JsName("CdbSsmListQueryResult")
class CdbSsmListQueryResult(
    override val ssmList: List<Ssm>
): CdbSsmListQueryResultDTO