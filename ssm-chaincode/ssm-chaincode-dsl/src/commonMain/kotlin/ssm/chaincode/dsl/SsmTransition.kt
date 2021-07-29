package ssm.chaincode.dsl

import kotlinx.serialization.Serializable
import kotlin.js.JsExport
import kotlin.js.JsName

expect interface SsmTransitionDTO {
	val from: Int
	val to: Int
	val role: String
	val action: String
}

@Serializable
@JsExport
@JsName("SsmTransition")
data class SsmTransition(
	override val from: Int,
	override val to: Int,
	override val role: String,
	override val action: String,
): SsmTransitionDTO
