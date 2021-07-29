package ssm.tx.dsl.model

import kotlinx.serialization.Serializable
import ssm.chaincode.dsl.SsmAgentDTO
import ssm.chaincode.dsl.SsmAgent
import kotlin.js.JsExport
import kotlin.js.JsName

expect interface TxSsmUserDTO {
    val agent: SsmAgentDTO
}

@Serializable
@JsExport
@JsName("TxSsmUser")
class TxSsmUser(
    override val agent: SsmAgent,
): TxSsmUserDTO
