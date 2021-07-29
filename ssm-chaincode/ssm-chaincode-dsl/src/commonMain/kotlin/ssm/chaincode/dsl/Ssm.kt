package ssm.chaincode.dsl

import kotlinx.serialization.Serializable
import kotlin.js.JsExport
import kotlin.js.JsName

expect interface SsmDTO {
	val name: String
	val transitions: List<SsmTransitionDTO>
}

@Serializable
@JsExport
@JsName("Ssm")
data class Ssm(
	override val name: String,
	override val transitions: List<SsmTransition>,
): SsmDTO