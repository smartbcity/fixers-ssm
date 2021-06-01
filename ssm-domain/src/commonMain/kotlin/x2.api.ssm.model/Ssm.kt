package x2.api.ssm.model

import kotlin.js.JsExport
import kotlin.js.JsName

@JsExport
@JsName("Ssm")
interface Ssm {
    val name: String
    val version: String
    val transitions: List<SsmTransition>
}

@JsExport
@JsName("SsmBase")
class SsmBase(
    override val name: String,
    override val version: String,
    override val transitions: List<SsmTransitionBase>
): Ssm
