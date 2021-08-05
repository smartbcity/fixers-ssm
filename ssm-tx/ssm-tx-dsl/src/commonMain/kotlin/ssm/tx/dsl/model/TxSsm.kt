package ssm.tx.dsl.model

import kotlinx.serialization.Serializable
import ssm.chaincode.dsl.Ssm
import ssm.chaincode.dsl.SsmDTO
import kotlin.js.JsExport
import kotlin.js.JsName

expect interface TxSsmDTO {
    /**
     * Description of a state machine
     */
    val ssm: SsmDTO

    /**
     * Channel in which the SSM has been instantiated
     */
    val channel: TxChannelDTO

    /**
     * Version of the SSM
     * @example "1.0"
     */
    val version: SsmVersion
}

/**
 * @d2 model
 * @page
 * Represents an [SSM][Ssm] with some metadata
 * @@title SSM-API/SSM
 */
@Serializable
@JsExport
@JsName("TxSsm")
class TxSsm(
	override val ssm: Ssm,
	override val channel: TxChannel,
	override val version: SsmVersion
): TxSsmDTO

typealias SsmVersion = String
