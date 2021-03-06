package ssm.dsl

import kotlinx.serialization.Serializable
import kotlin.js.JsExport
import kotlin.js.JsName

@Serializable
@JsExport
@JsName("Context")
data class Context (
	val session: String,
	val public: String,
	val iteration: Int,
	override val private: Map<String, String>? = null,
): WithPrivate