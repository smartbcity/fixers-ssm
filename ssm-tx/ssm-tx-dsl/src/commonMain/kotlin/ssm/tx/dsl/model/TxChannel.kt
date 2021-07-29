package ssm.tx.dsl.model

import kotlinx.serialization.Serializable
import kotlin.js.JsExport
import kotlin.js.JsName

typealias ChannelId = String

expect interface TxChannelDTO {
	val id: ChannelId
}

@Serializable
@JsExport
@JsName("TxChannel")
class TxChannel (
	override val id: ChannelId,
): TxChannelDTO