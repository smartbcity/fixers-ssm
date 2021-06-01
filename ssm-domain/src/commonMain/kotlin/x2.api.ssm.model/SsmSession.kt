package x2.api.ssm.model

import kotlin.js.JsExport
import kotlin.js.JsName

@JsExport
@JsName("SsmSession")
interface SsmSession {
    val id: SsmSessionId
    val channel: String
    val creationDate: Long
    val currentStep: SsmSessionStep
}

@JsExport
@JsName("SsmSessionBase")
class SsmSessionBase(
    override val id: SsmSessionId,
    override val channel: String,
    override val creationDate: Long,
    override val currentStep: SsmSessionStepBase
): SsmSession

typealias SsmSessionId = String
