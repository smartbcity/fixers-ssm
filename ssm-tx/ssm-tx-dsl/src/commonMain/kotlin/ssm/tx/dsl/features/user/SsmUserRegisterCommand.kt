package ssm.tx.dsl.features.user

import f2.dsl.fnc.F2Function
import ssm.chaincode.dsl.blockchain.TransactionId
import ssm.chaincode.dsl.model.Agent
import ssm.tx.dsl.features.SsmCommandDTO
import ssm.tx.dsl.features.SsmCommandResultDTO

/**
 * Creates an SSM
 * @d2 function
 * @parent [ssm.tx.dsl.SsmTxD2Command]
 * @title Register User
 */
typealias SsmTxUserRegisterFunction = F2Function<SsmUserRegisterCommand, SsmUserRegisteredResult>

/**
 * @d2 command
 * @parent [SsmTxUserRegisterFunction]
 * @title Register User: Parameters
 */
class SsmUserRegisterCommand(
	/**
	 * Admin signing the transaction
	 */
//	val signerAdmin: SignerAdmin,
	/**
	 * Initial user of the SSM
	 */
	val agent: Agent,
) : SsmCommandDTO

/**
 * @d2 event
 * @parent [SsmTxUserRegisterFunction]
 * @title Register User: Response
 */
class SsmUserRegisteredResult(
	override val transactionId: TransactionId
) : SsmCommandResultDTO
