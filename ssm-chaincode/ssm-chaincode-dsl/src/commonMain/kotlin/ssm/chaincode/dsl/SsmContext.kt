package ssm.chaincode.dsl

import kotlinx.serialization.Serializable
import kotlin.js.JsExport
import kotlin.js.JsName

expect interface SsmContextDTO: WithPrivate {
	val session: String
	val public: String
	val iteration: Int
	override val private: Map<String, String>?
}

@Serializable
@JsExport
@JsName("SsmContext")
data class SsmContext(
	override val session: String,
	override val public: String,
	override val iteration: Int,
	override val private: Map<String, String>? = null,
): SsmContextDTO