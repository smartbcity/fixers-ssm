import ssm.chaincode.dsl.Ssm
import ssm.chaincode.dsl.SsmBase
import kotlin.js.JsExport
import kotlin.js.JsName

@JsExport
@JsName("TxSsm")
interface TxSsm {
    val ssm: Ssm
    val channel: TxChannel
    val version: String
}

@JsExport
@JsName("TxSsmBase")
class TxSsmBase(
    override val ssm: SsmBase,
    override val channel: TxChannelBase,
    override val version: String
): TxSsm

typealias SsmVersion = String
typealias SsmName = String
