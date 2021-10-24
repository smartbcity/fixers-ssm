package ssm.tx.dsl.features.user

import f2.dsl.cqrs.Event
import f2.dsl.fnc.F2Function
import ssm.chaincode.dsl.model.InvokeReturn
import ssm.chaincode.dsl.model.Ssm
import ssm.chaincode.dsl.model.SsmAgent
import ssm.tx.dsl.features.SsmCommandDTO

/**
 * Grant user
 * @d2 function
 * @parent [Ssm]
 * @title Grant User
 */
typealias SsmTxUserGrantFunction = F2Function<SsmUserGrantCommand, SsmUserGrantedResult>

/**
 * @d2 command
 * @parent [SsmTxUserGrantFunction]
 * @title Grant User: Parameters
 */
class SsmUserGrantCommand(
	/**
	 * Initial user of the SSM
	 */
	val agent: SsmAgent,
) : SsmCommandDTO

/**
 * @d2 event
 * @parent [SsmUserGrantedResult]
 * @title Grant User: Response
 */
class SsmUserGrantedResult(
	val invokeReturn: InvokeReturn,
) : Event
