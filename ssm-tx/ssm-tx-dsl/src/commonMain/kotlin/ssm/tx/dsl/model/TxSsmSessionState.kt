package ssm.tx.dsl.model
import ssm.chaincode.dsl.SsmSessionState
import ssm.chaincode.dsl.SsmSessionStateBase
import ssm.chaincode.dsl.blockchain.Transaction
import ssm.chaincode.dsl.blockchain.TransactionBase
import kotlin.js.JsExport
import kotlin.js.JsName

@JsExport
@JsName("TxSsmSessionStateDTO")
interface TxSsmSessionStateDTO {
    val details: SsmSessionState
    val transaction: Transaction?
}

@JsExport
@JsName("TxSsmSessionState")
class TxSsmSessionState(
    override val details: SsmSessionStateBase,
    override val transaction: TransactionBase?
): TxSsmSessionStateDTO
