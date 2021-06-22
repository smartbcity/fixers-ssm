package ssm.chaincode.dsl

import kotlin.js.JsExport
import kotlin.js.JsName

typealias ChaincodeId = String

@JsExport
@JsName("ChaincodeDTO")
interface ChaincodeDTO {
	val id: ChaincodeId
	val channelId: ChannelId
}

@JsExport
@JsName("Chaincode")
data class Chaincode (
	override val id: ChaincodeId,
	override val channelId: ChannelId,
): ChaincodeDTO