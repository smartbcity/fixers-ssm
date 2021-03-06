package ssm.dsl

import kotlinx.serialization.Serializable
import kotlin.js.JsExport
import kotlin.js.JsName

@Serializable
@JsExport
@JsName("Transition")
data class SsmTransition(
	val from: Int,
	val to: Int,
	val role: String,
	val action: String,
)