package ssm.tx.dsl.model

import ssm.chaincode.dsl.Ssm
import ssm.chaincode.dsl.SsmBase
import kotlin.js.JsExport
import kotlin.js.JsName

@JsExport
@JsName("TxSsmDTO")
interface TxSsmDTO {
    val ssm: Ssm
    val channel: TxChannelDTO
    val version: SsmVersion
}

@JsExport
@JsName("TxSsm")
class TxSsm(
	override val ssm: SsmBase,
	override val channel: TxChannel,
	override val version: SsmVersion
): TxSsmDTO

typealias SsmVersion = String
