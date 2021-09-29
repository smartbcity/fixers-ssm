package ssm.data.dsl.model

import kotlin.js.JsExport
import kotlin.js.JsName
import kotlinx.serialization.Serializable
import ssm.chaincode.dsl.model.ChannelId

expect interface DataChannelDTO {
	/**
	 * Identifier
	 * @example "channel-smartb"
	 */
	val id: ChannelId
}

/**
 * Description of a channel
 * @d2 model
 * @parent [ssm.data.dsl.SsmApiQueryFunctions]
 */
@Serializable
@JsExport
@JsName("TxChannel")
class TxChannel(
	override val id: ChannelId,
) : DataChannelDTO
