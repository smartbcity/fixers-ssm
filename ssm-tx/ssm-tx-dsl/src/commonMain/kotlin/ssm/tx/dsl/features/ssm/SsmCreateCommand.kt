package ssm.tx.dsl.features.ssm

import f2.dsl.cqrs.Event
import f2.dsl.fnc.F2Function
import ssm.chaincode.dsl.model.InvokeReturn
import ssm.chaincode.dsl.model.Ssm
import ssm.tx.dsl.features.SsmCommandDTO

/**
 * Creates an SSM
 * @d2 function
 * @parent [Ssm]
 * @title Create SSM
 */
typealias SsmTxCreateFunction = F2Function<SsmCreateCommand, SsmCreateResult>

/**
 * @d2 command
 * @parent [SsmTxCreateFunction]
 * @title Create SSM: Parameters
 */
class SsmCreateCommand(
	/**
	 * Description of the SSM to create
	 */
	val ssm: Ssm,
) : SsmCommandDTO

/**
 * @d2 event
 * @parent [SsmTxCreateFunction]
 * @title Create SSM: Response
 */
class SsmCreateResult(
	val invokeReturn: List<InvokeReturn>,
) : Event
