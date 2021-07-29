package ssm.tx.dsl.model
import kotlinx.serialization.Serializable
import ssm.chaincode.dsl.SsmSessionStateDTO
import ssm.chaincode.dsl.SsmSessionStateBase
import ssm.chaincode.dsl.blockchain.TransactionDTO
import ssm.chaincode.dsl.blockchain.Transaction
import kotlin.js.JsExport
import kotlin.js.JsName

expect interface TxSsmSessionStateDTO {
    val details: SsmSessionStateDTO
    val transaction: TransactionDTO?
}

@Serializable
@JsExport
@JsName("TxSsmSessionState")
class TxSsmSessionState(
    override val details: SsmSessionStateBase,
    override val transaction: Transaction?
): TxSsmSessionStateDTO
