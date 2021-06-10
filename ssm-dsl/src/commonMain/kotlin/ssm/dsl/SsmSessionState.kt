package ssm.dsl

import kotlinx.serialization.Serializable
import kotlin.js.JsExport
import kotlin.js.JsName

@JsExport
@JsName("SsmSessionState")
interface SsmSessionState: SsmSession, WithPrivate  {
	override val ssm: String?
	override val session: String
	override val roles: Map<String, String>?
	override val public: Any?
	override val private: Map<String, String>?
	val origin: SsmTransition?
	val current: Int
	val iteration: Int
}

@Serializable
@JsExport
@JsName("SsmSessionStateBase")
data class SsmSessionStateBase(
	override val ssm: String?,
	override val session: String,
	override val roles: Map<String, String>?,
	override val public: Any?,
	override val private: Map<String, String>? = hashMapOf(),
	override val origin: SsmTransitionBase?,
	override val current: Int,
	override val iteration: Int,
): SsmSessionState