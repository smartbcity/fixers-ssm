package ssm.tx.dsl.features.ssm

import f2.dsl.cqrs.Event
import f2.dsl.fnc.F2Function
import ssm.chaincode.dsl.blockchain.TransactionId
import ssm.chaincode.dsl.model.Agent
import ssm.chaincode.dsl.model.Ssm
import ssm.tx.dsl.features.SsmCommandDTO
import ssm.tx.dsl.features.SsmCommandResult

/**
 * Initializes an SSM
 * @d2 function
 * @parent [ssm.tx.dsl.SsmTxD2Command]
 * @title Initialize SSM
 */
typealias SsmTxInitializeFunction = F2Function<SsmInitializeCommand, SsmInitializedResult>

/**
 * @d2 command
 * @parent [SsmTxInitializeFunction]
 * @title Initialize SSM: Parameters
 */
class SsmInitializeCommand(
	/**
	 * Description of the SSM to create
	 */
	val ssm: Ssm,

	/**
	 * Initial user of the SSM
	 */
	val agent: Agent,
) : SsmCommandDTO

/**
 * @d2 event
 * @parent [SsmTxInitializeFunction]
 * @title Initialize SSM: Response
 */
class SsmInitializedResult(
	val results: List<TransactionId>
) : Event
