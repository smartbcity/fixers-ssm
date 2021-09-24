package ssm.chaincode.dsl

import kotlin.js.JsExport
import kotlin.js.JsName
import kotlinx.serialization.Serializable

/**
 * The unique id of a chaincode.
 * @d2 model
 * @example "ssm"
 * @parent [Ssm]
 */
typealias ChaincodeId = String

expect interface ChaincodeDTO {
	val id: ChaincodeId
	val channelId: ChannelId
}

@Serializable
@JsExport
@JsName("Chaincode")
data class Chaincode(
	override val id: ChaincodeId,
	override val channelId: ChannelId,
) : ChaincodeDTO
