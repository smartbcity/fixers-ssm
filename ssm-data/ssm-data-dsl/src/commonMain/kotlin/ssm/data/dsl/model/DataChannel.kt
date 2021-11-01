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
 * @parent [ssm.data.dsl.SsmDataD2Model]
 */
@Serializable
@JsExport
@JsName("DataChannel")
class DataChannel(
	override val id: ChannelId,
) : DataChannelDTO
