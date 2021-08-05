package ssm.tx.dsl.model

import kotlinx.serialization.Serializable
import kotlin.js.JsExport
import kotlin.js.JsName

typealias ChannelId = String

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
 * @parent [ssm.tx.dsl.SsmApiFinder]
 */
@Serializable
@JsExport
@JsName("TxChannel")
class TxChannel (
	override val id: ChannelId,
): TxChannelDTO