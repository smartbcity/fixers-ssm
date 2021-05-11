package ssm.dsl

import kotlinx.serialization.Serializable
import kotlin.js.JsExport
import kotlin.js.JsName

@Serializable
@JsExport
@JsName("SsmSessionStateLog")
data class SsmSessionStateLog(
	val txId: String,
	val state: SsmSessionState
)