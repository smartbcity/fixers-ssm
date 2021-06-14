package ssm.chaincode.dsl

import kotlinx.serialization.Serializable
import kotlin.js.JsExport
import kotlin.js.JsName

@Serializable
@JsExport
@JsName("SsmGrant")
data class SsmGrant(
	val user: String,
	val iteration: Int,
	val credits: Map<String, Credit>,
)

@Serializable
@JsExport
@JsName("Credit")
data class Credit (
	val amount: Int
)