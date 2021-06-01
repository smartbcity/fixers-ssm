package x2.api.ssm.model

import kotlin.js.JsExport
import kotlin.js.JsName

@JsExport
@JsName("SsmTransition")
interface SsmTransition {
    val from: Int
    val to: Int
    val role: String
    val action: String
}

@JsExport
@JsName("SsmTransitionBase")
class SsmTransitionBase(
    override val from: Int,
    override val to: Int,
    override val role: String,
    override val action: String
): SsmTransition
