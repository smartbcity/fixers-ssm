package ssm.chaincode.dsl

import kotlinx.serialization.Serializable
import kotlin.js.JsExport
import kotlin.js.JsName

typealias ChaincodeId = String

expect interface ChaincodeDTO {
	val id: ChaincodeId
	val channelId: ChannelId
}

@Serializable
@JsExport
@JsName("Chaincode")
data class Chaincode (
	override val id: ChaincodeId,
	override val channelId: ChannelId,
): ChaincodeDTO