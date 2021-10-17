package ssm.tx.dsl.features.ssm

import f2.dsl.cqrs.Event
import f2.dsl.fnc.F2Function
import ssm.chaincode.dsl.model.InvokeReturn
import ssm.chaincode.dsl.model.SsmSession
import ssm.tx.dsl.features.SsmCommandDTO

/**
 * Create a session that instantiates a given ssm
 * @d2 function
 * @parent [SsmSession]
 * @title Start Session
 * @order 40
 */
typealias SsmTxSessionStartFunction = F2Function<SsmSessionStartCommand, SsmSessionStartResult>

/**
 * @d2 command
 * @parent [SsmTxSessionStartFunction]
 * @title Start Session: Parameters
 */
class SsmSessionStartCommand(
	/**
	 * Session to start
	 */
	val session: SsmSession,
) : SsmCommandDTO

/**
 * @d2 event
 * @parent [SsmTxSessionStartFunction]
 * @title Start Session: Response
 */
class SsmSessionStartResult(
	val invokeReturn: InvokeReturn,
) : Event
