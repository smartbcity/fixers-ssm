
import ssm.chaincode.dsl.SsmSessionState
import ssm.chaincode.dsl.blockchain.Transaction
import ssm.chaincode.dsl.blockchain.TransactionBase
import kotlin.js.JsExport
import kotlin.js.JsName

@JsExport
@JsName("TxSsmSessionState")
interface TxSsmSessionState {
    val details: SsmSessionState
    val transaction: Transaction?
}

@JsExport
@JsName("TxSsmSessionStateBase")
class TxSsmSessionStateBase(
    override val details: SsmSessionState,
    override val transaction: TransactionBase?
): TxSsmSessionState
