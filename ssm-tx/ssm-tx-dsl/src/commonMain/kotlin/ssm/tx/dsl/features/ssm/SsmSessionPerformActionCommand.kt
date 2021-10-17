package ssm.tx.dsl.features.ssm

import f2.dsl.cqrs.Event
import f2.dsl.fnc.F2Function
import ssm.chaincode.dsl.model.InvokeReturn
import ssm.chaincode.dsl.model.SsmContext
import ssm.chaincode.dsl.model.SsmSession
import ssm.tx.dsl.features.SsmCommandDTO

/**
 * Performs a state transition for a given session
 * @d2 function
 * @parent [SsmSession]
 * @title Perform Transition
 * @order 50
 */
typealias SsmTxSessionPerformActionFunction = F2Function<SsmSessionPerformActionCommand, SsmSessionPerformActionResult>

/**
 * @d2 command
 * @parent [SsmTxSessionPerformActionFunction]
 * @title Perform Transition: Parameters
 */
class SsmSessionPerformActionCommand(
	/**
	 * Transition to perform
	 * @example [ssm.chaincode.dsl.model.SsmTransition.action]
	 */
	val action: String,

	/**
	 * Information about the transition
	 */
	val context: SsmContext,
) : SsmCommandDTO

/**
 * @d2 event
 * @parent [SsmTxSessionPerformActionFunction]
 * @title Perform Transition: Response
 */
class SsmSessionPerformActionResult(
	val invokeReturn: InvokeReturn,
) : Event
