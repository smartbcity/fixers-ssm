package ssm.tx.dsl.features.user

import f2.dsl.fnc.F2Function
import ssm.chaincode.dsl.blockchain.TransactionId
import ssm.chaincode.dsl.model.Agent
import ssm.tx.dsl.features.SsmCommandDTO
import ssm.tx.dsl.features.SsmCommandResultDTO

/**
 * Grant user
 * @d2 function
 * @parent [ssm.tx.dsl.SsmTxD2Command]
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
	val agent: Agent,
) : SsmCommandDTO

/**
 * @d2 event
 * @parent [SsmTxUserGrantFunction]
 * @title Grant User: Response
 */
class SsmUserGrantedResult(
	override val transactionId: TransactionId,
) : SsmCommandResultDTO
