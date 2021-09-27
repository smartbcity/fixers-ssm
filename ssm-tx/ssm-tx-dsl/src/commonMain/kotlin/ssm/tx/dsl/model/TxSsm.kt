package ssm.tx.dsl.model

import kotlin.js.JsExport
import kotlin.js.JsName
import kotlinx.serialization.Serializable
import ssm.chaincode.dsl.model.Ssm
import ssm.chaincode.dsl.model.SsmDTO

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
 * @@title SSM-TX/SSM
 */
@Serializable
@JsExport
@JsName("TxSsm")
class TxSsm(
	override val ssm: Ssm,
	override val channel: TxChannel,
	override val version: SsmVersion,
) : TxSsmDTO

typealias SsmVersion = String
