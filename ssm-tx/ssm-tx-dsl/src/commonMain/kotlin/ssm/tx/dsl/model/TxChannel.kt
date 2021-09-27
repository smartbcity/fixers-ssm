package ssm.tx.dsl.model

import kotlin.js.JsExport
import kotlin.js.JsName
import kotlinx.serialization.Serializable
import ssm.chaincode.dsl.model.ChannelId

expect interface TxChannelDTO {
	/**
	 * Identifier
	 * @example "channel-smartb"
	 */
	val id: ChannelId
}

/**
 * Description of a channel
 * @d2 model
 * @parent [ssm.tx.dsl.SsmApiQueryFunctions]
 */
@Serializable
@JsExport
@JsName("TxChannel")
class TxChannel(
	override val id: ChannelId,
) : TxChannelDTO
