package ssm.chaincode.f2

import f2.dsl.cqrs.Event
import f2.dsl.fnc.F2Function
import ssm.chaincode.dsl.InvokeReturn
import ssm.chaincode.dsl.Ssm
import ssm.chaincode.dsl.SsmAgent
import ssm.chaincode.dsl.SsmChaincodeProperties
import ssm.chaincode.dsl.SsmCommandDTO
import ssm.sdk.sign.model.SignerAdmin

/**
 * Creates an SSM
 * @d2 function
 * @parent [Ssm]
 * @title Create SSM
 */
typealias SsmCreateFunction = F2Function<SsmCreateCommand, SsmCreateResult>

/**
 * @d2 command
 * @parent [SsmCreateFunction]
 * @title Create SSM: Parameters
 */
class SsmCreateCommand(
	/**
	 * Description of the SSM to create
	 */
	val ssm: Ssm,

	/**
	 * Admin signing the transaction
	 */
	val signerAdmin: SignerAdmin,

	/**
	 * Initial user of the SSM
	 */
	val agent: SsmAgent,
	override val chaincode: SsmChaincodeProperties,
	override val bearerToken: String?,
) : SsmCommandDTO

/**
 * @d2 event
 * @parent [SsmCreateFunction]
 * @title Create SSM: Response
 */
class SsmCreateResult(
	val invokeReturn: List<InvokeReturn>,
) : Event
