package ssm.dsl

import kotlinx.serialization.Serializable
import kotlin.js.JsExport
import kotlin.js.JsName

@Serializable
@JsExport
@JsName("Ssm")
data class Ssm(
	val name: String,
	val transitions: List<SsmTransition>,
)