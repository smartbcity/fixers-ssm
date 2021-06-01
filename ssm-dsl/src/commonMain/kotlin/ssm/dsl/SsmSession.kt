package ssm.dsl

import kotlinx.serialization.Serializable
import kotlin.js.JsExport
import kotlin.js.JsName

@Serializable
@JsExport
@JsName("SsmSession")
open class SsmSession (
	open val ssm: String?,
	open val session: String,
	open val roles: Map<String, String>?,
	open val public: Any?,
	override val private: Map<String, String>? = hashMapOf(),
): WithPrivate