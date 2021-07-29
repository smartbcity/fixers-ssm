package ssm.chaincode.dsl

import kotlinx.serialization.Serializable
import kotlin.js.JsExport
import kotlin.js.JsName

/**
 * @D2 model
 * @parent [Ssm]
 * @title SSM Transition
 */
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
	 * Role of the user allowed to trigger the transition
	 * @example "Seller"
	 */
	val role: String

	/**
	 * Trigger of the transition
	 * @example "Sell"
	 */
	val action: String
}

@Serializable
@JsExport
@JsName("SsmTransition")
data class SsmTransition(
	override val from: Int,
	override val to: Int,
	override val role: String,
	override val action: String,
): SsmTransitionDTO
