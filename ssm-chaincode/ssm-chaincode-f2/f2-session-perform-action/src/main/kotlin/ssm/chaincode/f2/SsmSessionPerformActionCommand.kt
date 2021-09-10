package ssm.chaincode.f2

import f2.dsl.cqrs.Event
import f2.dsl.fnc.F2Function
import ssm.chaincode.dsl.InvokeReturn
import ssm.chaincode.dsl.SsmChaincodeProperties
import ssm.chaincode.dsl.SsmCommandDTO
import ssm.chaincode.dsl.SsmContext
import ssm.chaincode.dsl.SsmSession
import ssm.sdk.sign.model.Signer

/**
 * Performs a state transition for a given session
 * @d2 function
 * @parent [SsmSession]
 * @title Perform Transition
 * @order 50
 */
typealias SsmSessionPerformActionFunction = F2Function<SsmSessionPerformActionCommand, SsmSessionPerformActionResult>

/**
 * @d2 command
 * @parent [SsmSessionPerformActionFunction]
 * @title Perform Transition: Parameters
 */
class SsmSessionPerformActionCommand(
	override val chaincode: SsmChaincodeProperties,
	override val bearerToken: String?,

	/**
	 * Signer of the transaction
	 */
	val signer: Signer,

	/**
	 * Transition to perform
	 * @example [ssm.chaincode.dsl.SsmTransition.action]
	 */
	val action: String,

	/**
	 * Information about the transition
	 */
	val context: SsmContext,
) : SsmCommandDTO

/**
 * @d2 event
 * @parent [SsmSessionPerformActionFunction]
 * @title Perform Transition: Response
 */
class SsmSessionPerformActionResult(
	val invokeReturn: InvokeReturn,
) : Event
