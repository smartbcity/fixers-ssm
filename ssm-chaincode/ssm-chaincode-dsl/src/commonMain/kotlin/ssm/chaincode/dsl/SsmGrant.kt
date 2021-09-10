package ssm.chaincode.dsl

import kotlin.js.JsExport
import kotlin.js.JsName
import kotlinx.serialization.Serializable

expect interface SsmGrantDTO {
	val user: String
	val iteration: Int
	val credits: Map<String, CreditDTO>
}

@Serializable
@JsExport
@JsName("SsmGrant")
data class SsmGrant(
	val user: String,
	val iteration: Int,
	val credits: Map<String, Credit>,
)

expect interface CreditDTO {
	val amount: Int
}

@Serializable
@JsExport
@JsName("Credit")
data class Credit(
	override val amount: Int,
) : CreditDTO
