package ssm.dsl

import kotlinx.serialization.Serializable
import kotlin.js.JsExport
import kotlin.js.JsName

@JsExport
@JsName("Context")
interface Context: WithPrivate {
	val session: String
	val public: String
	val iteration: Int
	override val private: Map<String, String>?
}

@Serializable
@JsExport
@JsName("SsmContextBase")
data class SsmContextBase(
	override val session: String,
	override val public: String,
	override val iteration: Int,
	override val private: Map<String, String>? = null,
): Context