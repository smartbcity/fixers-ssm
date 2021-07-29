package ssm.tx.dsl.model

import kotlinx.serialization.Serializable
import ssm.chaincode.dsl.SsmDTO
import ssm.chaincode.dsl.Ssm
import kotlin.js.JsExport
import kotlin.js.JsName

expect interface TxSsmDTO {
    val ssm: SsmDTO
    val channel: TxChannelDTO
    val version: SsmVersion
}

@Serializable
@JsExport
@JsName("TxSsm")
class TxSsm(
	override val ssm: Ssm,
	override val channel: TxChannel,
	override val version: SsmVersion
): TxSsmDTO

typealias SsmVersion = String
