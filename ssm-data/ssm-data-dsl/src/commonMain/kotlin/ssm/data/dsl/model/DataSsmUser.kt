package ssm.data.dsl.model

import kotlin.js.JsExport
import kotlin.js.JsName
import kotlinx.serialization.Serializable
import ssm.chaincode.dsl.model.SsmAgent
import ssm.chaincode.dsl.model.SsmAgentDTO

expect interface DataSsmUserDTO {
	/**
	 * Agent able to interact with an SSM
	 */
	val agent: SsmAgentDTO
}

/**
 * Represents an [SSM Agent][SsmAgent]
 * @d2 model
 * @parent [ssm.data.dsl.SsmApiQueryFunctions]
 */
@Serializable
@JsExport
@JsName("DataSsmUser")
class DataSsmUser(
	override val agent: SsmAgent,
) : DataSsmUserDTO
