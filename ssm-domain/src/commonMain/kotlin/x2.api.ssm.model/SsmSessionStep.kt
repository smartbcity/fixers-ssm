package x2.api.ssm.model

import kotlin.js.JsExport
import kotlin.js.JsName

@JsExport
@JsName("SsmSessionStep")
interface SsmSessionStep {
    val id: Int
    val date: Long
    val user: String
}

@JsExport
@JsName("SsmSessionStepBase")
class SsmSessionStepBase(
    override val id: Int,
    override val date: Long,
    override val user: String
): SsmSessionStep
