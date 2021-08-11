package ssm.chaincode.dsl

import kotlinx.serialization.Serializable
import kotlin.js.JsExport
import kotlin.js.JsName

expect interface SsmDTO {
	/**
	 * Unique identifier for the SSM.
	 * @example "ProductLogistic"
	 */
	val name: String

	/**
	 * The definition of the State Machine
	 * @example [
	 * 	{
	 * 		"from": 0,
	 * 		"to": 1,
	 * 		"role": "Provider",
	 * 		"action": "Build"
	 * 	}, {
	 * 		"from": 1,
	 * 		"to": 2,
	 * 		"role": "Seller",
	 * 		"action": "Sell"
	 * 	}, {
	 * 		"from": 2,
	 * 	    "to": 3,
	 * 	    "role": "Buyer",
	 * 	    "action": "Buy"
	 * 	}
	 * ]
	 */
	val transitions: List<SsmTransitionDTO>
}

/**
 * @D2 model
 * @page
 * In a blockchain, smart-contracts are programs designed to implement a transaction between several parties.
 * Smart-contracts are usually written in programming languages such as Solidity for Ethereum, Golang (Go) or Node.js for Hyperledger fabric based blockchains,
 * or almost about any language according to the underlying blockchain implementation. \
 * A Signing State Machine (SSM) is a smart contract written with a more constrained paradigm than plain programming languages, based on a finite state automaton.
 * @@title Chaincode-DSL/Signing State Machine
 */
@Serializable
@JsExport
@JsName("Ssm")
data class Ssm(
	override val name: String,
	override val transitions: List<SsmTransition>,
): SsmDTO