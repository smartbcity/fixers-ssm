package ssm.dsl

import kotlinx.serialization.Serializable
import kotlin.js.JsExport
import kotlin.js.JsName

@Serializable
@JsExport
@JsName("SessionState")
data class SessionState(
	override val ssm: String,
	override val session: String,
	override val roles: Map<String, String>,
	override val public: String,
	override val private: Map<String, String>? = hashMapOf(),
	val origin: Transition?,
	val current: Int,
	val iteration: Int,
): Session(ssm, session, roles, public, private), WithPrivate