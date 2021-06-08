package ssm.dsl

import kotlinx.serialization.Serializable
import kotlin.js.JsExport
import kotlin.js.JsName

@JsExport
@JsName("SsmSessionState")
interface SsmSessionState: SsmSession, WithPrivate  {
	override val ssm: String
	override val session: String
	override val roles: Map<String, String>
	override val public: String
	override val private: Map<String, String>?
	val origin: SsmTransition?
	val current: Int
	val iteration: Int
}

@Serializable
@JsExport
@JsName("SsmSessionStateBase")
open class SsmSessionStateBase(
	override val ssm: String,
	override val session: String,
	override val roles: Map<String, String>,
	override val public: String,
	override val private: Map<String, String>? = hashMapOf(),
	override val origin: SsmTransition?,
	override val current: Int,
	override val iteration: Int,
): SsmSessionState