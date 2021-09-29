package ssm.data.dsl.features.query

import f2.dsl.fnc.F2Function
import kotlin.js.JsExport
import kotlin.js.JsName
import kotlinx.serialization.Serializable
import ssm.chaincode.dsl.model.ChaincodeId
import ssm.chaincode.dsl.model.ChannelId
import ssm.data.dsl.model.DataSsm
import ssm.data.dsl.model.DataSsmDTO

/**
 * Retrieves all known SSMs
 * @d2 function
 * @parent [DataSsm]
 * @order 20
 * @title List SSMs
 */
typealias DataSsmListQueryFunction = F2Function<DataSsmListQuery, DataSsmListQueryResult>

expect interface DataSsmListQueryDTO {
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
 * @parent [DataSsmListQueryFunction]
 * @title List SSMs: Parameters
 */
@Serializable
@JsExport
@JsName("DataSsmListQuery")
class DataSsmListQuery(
	override val channelId: ChannelId,
	override val chaincodeId: ChaincodeId
	) : DataSsmListQueryDTO

expect interface DataSsmListQueryResultDTO {
	/**
	 * List of all retrieved SSMs
	 */
	val list: List<DataSsmDTO>
}

/**
 * @d2 event
 * @parent [DataSsmListQueryFunction]
 * @title List SSMs: Result
 */
@Serializable
@JsExport
@JsName("DataSsmListQueryResult")
class DataSsmListQueryResult(
	override val list: List<DataSsm>,
) : DataSsmListQueryResultDTO
