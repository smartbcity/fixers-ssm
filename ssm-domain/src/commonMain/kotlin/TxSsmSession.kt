import ssm.dsl.SsmSessionState
import ssm.dsl.SsmSessionStateBase
import kotlin.js.JsExport
import kotlin.js.JsName


@JsExport
@JsName("TxSsmSession")
interface TxSsmSession {
    //TODO We should have SsmSession?
    val state: SsmSessionState
    val channel: TxChannel
    val creationDate: Long
    //TODO We should have List<SsmTransaction?
    val lastTransaction: TxSsmTransaction
}

@JsExport
@JsName("TxSsmSessionBase")
class TxSsmSessionBase(
    override val state: SsmSessionStateBase,
    override val channel: TxChannelBase,
    override val creationDate: Long,
    override val lastTransaction: TxSsmTransactionBase
): TxSsmSession
