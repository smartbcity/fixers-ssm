package ssm.chaincode.dsl

import kotlin.js.JsExport
import kotlin.js.JsName
import kotlinx.serialization.Serializable

expect interface SsmTransitionDTO {
	/**
	 * Origin of the transition
	 * @example 1
	 */
	val from: Int

	/**
	 * Destination of the transition
	 * @example 2
	 */
	val to: Int

	/**
	 * Role of the [agent][SsmAgent] allowed to trigger the transition
	 * @example "Seller"
	 */
	val role: String

	/**
	 * Trigger of the transition
	 * @example "Sell"
	 */
	val action: String
}

/**
 * @D2 model
 * @parent [Ssm]
 */
@Serializable
@JsExport
@JsName("SsmTransition")
data class SsmTransition(
	override val from: Int,
	override val to: Int,
	override val role: String,
	override val action: String,
) : SsmTransitionDTO
