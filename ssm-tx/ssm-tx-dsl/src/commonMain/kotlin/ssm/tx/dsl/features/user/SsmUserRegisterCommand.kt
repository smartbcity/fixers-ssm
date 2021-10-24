package ssm.tx.dsl.features.user

import f2.dsl.cqrs.Event
import f2.dsl.fnc.F2Function
import ssm.chaincode.dsl.model.InvokeReturn
import ssm.chaincode.dsl.model.Ssm
import ssm.chaincode.dsl.model.SsmAgent
import ssm.tx.dsl.features.SsmCommandDTO

/**
 * Creates an SSM
 * @d2 function
 * @parent [Ssm]
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
	val agent: SsmAgent,
) : SsmCommandDTO

/**
 * @d2 event
 * @parent [SsmUserRegisteredResult]
 * @title Register User: Response
 */
class SsmUserRegisteredResult(
	val invokeReturn: InvokeReturn,
) : Event
