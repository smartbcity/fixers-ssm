package ssm.data.dsl.features.query

import f2.dsl.fnc.F2Function
import kotlin.js.JsExport
import kotlin.js.JsName
import kotlinx.serialization.Serializable
import ssm.chaincode.dsl.model.uri.SsmUri
import ssm.data.dsl.model.DataSsmSessionId
import ssm.data.dsl.model.DataSsmSessionState

/**
 * Retrieve all the logs of a given SSM session
 * @d2 function
 * @parent [ssm.data.dsl.SsmDataD2Query]
 * @order 40
 * @title List Session Logs
 */
typealias DataSsmSessionLogListQueryFunction = F2Function<DataSsmSessionLogListQueryDTO, DataSsmSessionLogListQueryResultDTO>

expect interface DataSsmSessionLogListQueryDTO : DataQueryDTO {
	/**
	 * Identifier of the session to retrieve
	 * @example "DataSsmSessionId"
	 */
	val sessionId: DataSsmSessionId
	override val ssm: SsmUri
}

/**
 * @d2 query
 * @parent [DataSsmSessionLogListQueryFunction]
 * @title List Session Logs: Parameters
 */
@Serializable
@JsExport
@JsName("DataSsmSessionLogListQuery")
class DataSsmSessionLogListQuery(
	override val sessionId: DataSsmSessionId,
	override val ssm: SsmUri,
) : DataSsmSessionLogListQueryDTO

expect interface DataSsmSessionLogListQueryResultDTO {
	/**
	 * All retrieved logs of the given session
	 */
	val items: List<DataSsmSessionState>
}

/**
 * @d2 event
 * @parent [DataSsmSessionLogListQueryFunction]
 * @title List Session Logs: Result
 */
@Serializable
@JsExport
@JsName("SsmSessionLogListQueryResult")
class DataSsmSessionLogListQueryResult(
	override val items: List<DataSsmSessionState>,
) : DataSsmSessionLogListQueryResultDTO
