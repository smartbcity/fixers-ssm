package ssm.chaincode.dsl

import kotlinx.serialization.Serializable
import kotlin.js.JsExport
import kotlin.js.JsName

expect interface SsmSessionDTO: WithPrivate {
	val ssm: String?
	val session: String
	val roles: Map<String, String>?
	val public: Any?
	override val private: Map<String, String>?
}

@Serializable
@JsExport
@JsName("SsmSession")
open class SsmSession(
	override val ssm: String,
	override val session: String,
	override val roles: Map<String, String>,
	override val public: String,
	override val private: Map<String, String>? = hashMapOf(),
): SsmSessionDTO

