package ssm.chaincode.dsl

import kotlinx.serialization.Serializable
import kotlin.js.JsExport
import kotlin.js.JsName

expect interface InvokeReturnDTO {
	val status: String
	val info: String
	val transactionId: String
}

@Serializable
@JsExport
@JsName("InvokeReturn")
class InvokeReturn(
	val status: String,
	val info: String,
	val transactionId: String
)