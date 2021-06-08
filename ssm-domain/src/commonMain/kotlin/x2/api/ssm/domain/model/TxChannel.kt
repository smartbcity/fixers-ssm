package x2.api.ssm.domain.model

import kotlin.js.JsExport
import kotlin.js.JsName

typealias ChannelId = String

@JsExport
@JsName("TxChannel")
interface TxChannel {
	val id: ChannelId
}

@JsExport
@JsName("TxChannelBase")
class TxChannelBase (
	override val id: ChannelId,
) : TxChannel