package ssm.data.dsl.features.query

import f2.dsl.fnc.F2Function
import kotlin.js.JsExport
import kotlin.js.JsName
import kotlinx.serialization.Serializable
import ssm.chaincode.dsl.model.ChannelId
import ssm.data.dsl.model.DataSsm

/**
 * Retrieves a given SSM
 * @d2 function
 * @parent [DataSsm]
 * @order 10
 * @title Get SSM
 */
typealias DataSsmGetQueryFunction = F2Function<DataSsmGetQueryDTO, DataSsmGetQueryResultDTO>

expect interface DataSsmGetQueryDTO : DataQueryDTO {
	override val ssm: SsmName
	override val bearerToken: String?
}

/**
 * @d2 query
 * @parent [DataSsmGetQueryFunction]
 * @title Get SSM: Parameters
 */
@Serializable
@JsExport
@JsName("DataSsmGetQuery")
class DataSsmGetQuery(

	override val ssm: SsmName,
	override val bearerToken: String?,
) : DataSsmGetQueryDTO

expect interface DataSsmGetQueryResultDTO {
	/**
	 * The retrieved SSM if it exists
	 */
	val ssm: DataSsm?
}

/**
 * @d2 event
 * @parent [DataSsmGetQueryFunction]
 * @title Get SSM: Result
 */
@Serializable
@JsExport
@JsName("DataSsmGetQueryResult")
class DataSsmGetQueryResult(
	override val ssm: DataSsm?,
) : DataSsmGetQueryResultDTO
