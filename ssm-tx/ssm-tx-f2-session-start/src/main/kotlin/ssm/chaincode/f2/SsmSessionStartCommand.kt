package ssm.chaincode.f2

import f2.dsl.cqrs.Event
import f2.dsl.fnc.F2Function
import ssm.chaincode.dsl.model.InvokeReturn
import ssm.chaincode.dsl.SsmCommandDTO
import ssm.chaincode.dsl.model.SsmSession
import ssm.sdk.sign.model.SignerAdmin

/**
 * Create a session that instantiates a given ssm
 * @d2 function
 * @parent [SsmSession]
 * @title Start Session
 * @order 40
 */
typealias SsmSessionStartFunction = F2Function<SsmSessionStartCommand, SsmSessionStartResult>

/**
 * @d2 command
 * @parent [SsmSessionStartFunction]
 * @title Start Session: Parameters
 */
class SsmSessionStartCommand(
	override val bearerToken: String?,

	/**
	 * Session to start
	 */
	val session: SsmSession,

	/**
	 * Admin signing the transaction
	 */
	val signerAdmin: SignerAdmin,
) : SsmCommandDTO

/**
 * @d2 event
 * @parent [SsmSessionStartFunction]
 * @title Start Session: Response
 */
class SsmSessionStartResult(
	val invokeReturn: InvokeReturn,
) : Event
