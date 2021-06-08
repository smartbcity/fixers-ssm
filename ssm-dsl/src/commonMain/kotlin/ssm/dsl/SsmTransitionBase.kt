package ssm.dsl

import kotlinx.serialization.Serializable
import kotlin.js.JsExport
import kotlin.js.JsName

@JsExport
@JsName("SsmTransition")
interface SsmTransition {
	val from: Int
	val to: Int
	val role: String
	val action: String
}

@Serializable
@JsExport
@JsName("SsmTransitionBase")
open class SsmTransitionBase(
	override val from: Int,
	override val to: Int,
	override val role: String,
	override val action: String,
): SsmTransition
