package ssm.dsl

import kotlinx.serialization.Serializable
import kotlin.js.JsExport
import kotlin.js.JsName

@JsExport
@JsName("Ssm")
interface Ssm {
	val name: String
	val transitions: List<SsmTransition>
}

@Serializable
@JsExport
@JsName("SsmBase")
data class SsmBase(
	override val name: String,
	override val transitions: List<SsmTransition>,
): Ssm