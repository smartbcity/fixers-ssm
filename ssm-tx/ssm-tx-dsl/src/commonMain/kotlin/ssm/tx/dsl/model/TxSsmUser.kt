package ssm.tx.dsl.model

import kotlinx.serialization.Serializable
import ssm.chaincode.dsl.SsmAgent
import ssm.chaincode.dsl.SsmAgentDTO
import kotlin.js.JsExport
import kotlin.js.JsName

expect interface TxSsmUserDTO {
    /**
     * Agent able to interact with an SSM
     */
    val agent: SsmAgentDTO
}

/**
 * Represents an [SSM Agent][SsmAgent]
 * @d2 model
 * @parent [ssm.tx.dsl.SsmApiFinder]
 */
@Serializable
@JsExport
@JsName("TxSsmUser")
class TxSsmUser(
    override val agent: SsmAgent,
): TxSsmUserDTO
