package ssm.chaincode.dsl

import kotlinx.serialization.Serializable
import kotlin.js.JsExport
import kotlin.js.JsName

expect interface SsmSessionStateDTO: SsmSessionDTO, WithPrivate  {
	override val ssm: String?
	override val session: String
	override val roles: Map<String, String>?
	override val public: Any?
	override val private: Map<String, String>?
	val origin: SsmTransitionDTO?
	val current: Int
	val iteration: Int
}

@Serializable
@JsExport
@JsName("SsmSessionState")
data class SsmSessionState(
	override val ssm: String?,
	override val session: String,
	override val roles: Map<String, String>?,
	override val public: Any?,
	override val private: Map<String, String>? = hashMapOf(),
	override val origin: SsmTransition?,
	override val current: Int,
	override val iteration: Int,
): SsmSessionStateDTO