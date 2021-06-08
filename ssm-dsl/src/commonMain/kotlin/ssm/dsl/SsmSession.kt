package ssm.dsl

import kotlinx.serialization.Serializable
import kotlin.js.JsExport
import kotlin.js.JsName

@JsExport
@JsName("SsmSession")
interface SsmSession: WithPrivate {
	val ssm: String?
	val session: String
	val roles: Map<String, String>?
	val public: Any?
	override val private: Map<String, String>?
}

@Serializable
@JsExport
@JsName("SsmSessionBase")
open class SsmSessionBase(
	override val ssm: String,
	override val session: String,
	override val roles: Map<String, String>,
	override val public: String,
	override val private: Map<String, String>? = hashMapOf(),
): SsmSession

