package ssm.chaincode.dsl

import kotlinx.serialization.Serializable
import kotlin.js.JsExport
import kotlin.js.JsName

expect interface SsmTransitionDTO {
	val from: Int
	val to: Int
	val role: String
	val action: String
}

/**
 * @D2 model
 * @parent [Ssm]
 * @title SSM Transition
 */
@Serializable
@JsExport
@JsName("SsmTransition")
data class SsmTransition(
	/**
	 * Origin of the transition
	 * @example 1
	 */
	override val from: Int,

	/**
	 * Destination of the transition
	 * @example 2
	 */
	override val to: Int,

	/**
	 * Role of the user allowed to trigger the transition
	 * @example "Seller"
	 */
	override val role: String,

	/**
	 * Trigger of the transition
	 * @example "Sell"
	 */
	override val action: String,
): SsmTransitionDTO
