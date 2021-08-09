package ssm.couchdb.dsl.query

import f2.dsl.fnc.F2Function
import kotlinx.serialization.Serializable
import ssm.chaincode.dsl.SsmSessionState
import ssm.couchdb.dsl.CdbQueryDTO
import kotlin.js.JsExport
import kotlin.js.JsName

/**
 * Retrieve the list of all known sessions of a given SSM
 * @d2 function
 * @parent [ssm.couchdb.dsl.Database]
 * @order 20
 * @title List Sessions
 */
typealias CdbSsmSessionListQueryFunction = F2Function<CdbSsmSessionListQueryDTO, CdbSsmSessionListQueryResultDTO>

expect interface CdbSsmSessionListQueryDTO: CdbQueryDTO{
    /**
     * Name of the database to query on
     * @example [ssm.couchdb.dsl.Database.name]
     */
    val dbName: String

    /**
     * Identifier of the SSM
     * @example [ssm.chaincode.dsl.Ssm.name]
     */
    val ssm: String?
    override val dbConfig: String
}

/**
 * @d2 query
 * @parent [CdbSsmSessionListQueryFunction]
 * @title List Sessions: Parameters
 */
@Serializable
@JsExport
@JsName("CdbSsmSessionListQuery")
class CdbSsmSessionListQuery(
    override val dbName: String,
    override val ssm: String?,
    override val dbConfig: String
): CdbSsmSessionListQueryDTO

expect interface CdbSsmSessionListQueryResultDTO{
    /**
     * Retrieved sessions
     */
    val sessions: Array<SsmSessionState>
}

/**
 * @d2 event
 * @parent [CdbSsmSessionListQueryFunction]
 * @title List Sessions: Result
 */
@Serializable
@JsExport
@JsName("CdbSsmSessionListQueryResult")
class CdbSsmSessionListQueryResult(
    override val sessions: Array<SsmSessionState>
): CdbSsmSessionListQueryResultDTO