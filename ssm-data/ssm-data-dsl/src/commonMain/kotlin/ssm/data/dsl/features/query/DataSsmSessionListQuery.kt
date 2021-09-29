package ssm.data.dsl.features.query

import f2.dsl.fnc.F2Function
import kotlin.js.JsExport
import kotlin.js.JsName
import kotlinx.serialization.Serializable
import ssm.chaincode.dsl.model.ChaincodeId
import ssm.chaincode.dsl.model.ChannelId
import ssm.data.dsl.model.DataSsmSession
import ssm.data.dsl.model.DataSsmSessionDTO

/**
 * Retrieve a list of all known sessions of a given SSM
 * @d2 function
 * @parent [DataSsmSession]
 * @order 20
 * @title List Sessions
 */
typealias DataSsmSessionListQueryFunction = F2Function<DataSsmSessionListQueryDTO, DataSsmSessionListQueryResultDTO>

expect interface DataSsmSessionListQueryDTO : ssm.data.dsl.features.query.DataQueryDTO {
	/**
	 * Identifier of the channel hosting the chaincode
	 * @example "channel-smartb"
	 */
	val channelId: ChannelId

	/**
	 * Identifier of the chaincode
	 * @example "ssmsmartb"
	 */
	val chaincodeId: ChaincodeId
}

/**
 * @d2 query
 * @parent [DataSsmSessionListQueryFunction]
 * @title List Sessions: Parameters
 */
@Serializable
@JsExport
@JsName("DataSsmSessionListQuery")
class DataSsmSessionListQuery(
	override val ssm: String,
	override val bearerToken: String?,
	override val channelId: ChannelId,
	override val chaincodeId: ChaincodeId,
) : DataSsmSessionListQueryDTO

expect interface DataSsmSessionListQueryResultDTO {
	/**
	 * List of all the retrieved sessions
	 */
	val list: List<DataSsmSessionDTO>
}

/**
 * @d2 event
 * @parent [DataSsmSessionListQueryFunction]
 * @title List Sessions: Result
 */
@Serializable
@JsExport
@JsName("DataSsmSessionListQueryResult")
class DataSsmSessionListQueryResult(
	override val list: List<DataSsmSession>,
) : DataSsmSessionListQueryResultDTO
