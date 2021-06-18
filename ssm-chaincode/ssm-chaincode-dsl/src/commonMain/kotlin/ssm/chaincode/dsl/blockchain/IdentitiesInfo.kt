package ssm.chaincode.dsl.blockchain

import kotlin.js.JsExport
import kotlin.js.JsName

@JsExport
@JsName("IdentitiesInfo")
interface IdentitiesInfo {
    val id: String
    val mspid: String
}

@JsExport
@JsName("IdentitiesInfoBase")
class IdentitiesInfoBase(
    override val id: String,
    override val mspid: String
): IdentitiesInfo
