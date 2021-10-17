package ssm.data.dsl.features.query

import f2.dsl.fnc.F2Function
import kotlin.js.JsExport
import kotlin.js.JsName
import kotlinx.serialization.Serializable
import ssm.chaincode.dsl.model.SessionName
import ssm.chaincode.dsl.model.uri.SsmUri
import ssm.data.dsl.model.DataSsmSession
import ssm.data.dsl.model.DataSsmSessionDTO

/**
 * Retrieves a given session
 * @d2 function
 * @parent [DataSsmSession]
 * @order 10
 * @title Get Session
 */
typealias DataSsmSessionGetQueryFunction = F2Function<DataSsmSessionGetQueryDTO, DataSsmSessionGetQueryResultDTO>

expect interface DataSsmSessionGetQueryDTO : DataQueryDTO {
	/**
	 * Identifier of the session to retrieve
	 * @example [DataSsmSession.id]
	 */
	val sessionId: SessionName
	override val ssm: SsmUri
	override val bearerToken: String?
}

/**
 * @d2 query
 * @parent [DataSsmSessionGetQueryFunction]
 * @title Get Session: Parameters
 */
@Serializable
@JsExport
@JsName("DataSsmSessionGetQuery")
class DataSsmSessionGetQuery(
	override val sessionId: SessionName,
	override val ssm: SsmUri,
	override val bearerToken: String?,
) : DataSsmSessionGetQueryDTO

expect interface DataSsmSessionGetQueryResultDTO {
	/**
	 * The retrieved session if it exists
	 */
	val session: DataSsmSessionDTO?
}

/**
 * @d2 event
 * @parent [DataSsmSessionGetQueryFunction]
 * @title Get Session: Result
 */
@Serializable
@JsExport
@JsName("DataSsmSessionGetQueryResult")
class DataSsmSessionGetQueryResult(
	override val session: DataSsmSession?,
) : DataSsmSessionGetQueryResultDTO
