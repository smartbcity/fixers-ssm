package ssm.tx.dsl.model

import kotlin.js.JsExport
import kotlin.js.JsName

typealias ChannelId = String

@JsExport
@JsName("TxChannelDTO")
interface TxChannelDTO {
	val id: ChannelId
}

@JsExport
@JsName("TxChannel")
class TxChannel (
	override val id: ChannelId,
): TxChannelDTO