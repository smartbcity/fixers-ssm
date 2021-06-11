import ssm.dsl.blockchain.Transaction
import ssm.dsl.blockchain.TransactionBase
import kotlin.js.JsExport
import kotlin.js.JsName

@JsExport
@JsName("TxSsmSession")
interface TxSsmSession {
    val id: TxSsmSessionId
    val currentState: TxSsmSessionState
    val channel: TxChannel
    val creationTransaction: Transaction?
}

@JsExport
@JsName("TxSsmSessionBase")
class TxSsmSessionBase(
    override val id: TxSsmSessionId,
    override val currentState: TxSsmSessionStateBase,
    override val channel: TxChannelBase,
    override val creationTransaction: TransactionBase?,
): TxSsmSession

typealias TxSsmSessionId = String
