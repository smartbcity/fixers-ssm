package ssm.tx.dsl.model

import ssm.chaincode.dsl.SsmAgent
import ssm.chaincode.dsl.SsmAgentBase
import kotlin.js.JsExport
import kotlin.js.JsName

@JsExport
@JsName("TxSsmUserDTO")
interface TxSsmUserDTO {
    val agent: SsmAgent
}

@JsExport
@JsName("TxSsmUser")
class TxSsmUser(
    override val agent: SsmAgentBase,
): TxSsmUserDTO
