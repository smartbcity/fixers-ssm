package ssm.chaincode.dsl.blockchain

import kotlinx.serialization.Serializable
import kotlin.js.JsExport
import kotlin.js.JsName

expect interface IdentitiesInfoDTO {
    val id: String
    val mspid: String
}

@Serializable
@JsExport
@JsName("IdentitiesInfo")
class IdentitiesInfo(
    override val id: String,
    override val mspid: String
): IdentitiesInfoDTO
