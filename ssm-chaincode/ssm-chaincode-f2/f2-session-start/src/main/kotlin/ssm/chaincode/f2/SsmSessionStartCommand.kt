package ssm.chaincode.f2

import f2.dsl.cqrs.Event
import f2.dsl.function.F2Function
import ssm.chaincode.dsl.InvokeReturn
import ssm.chaincode.dsl.SsmCommand
import ssm.chaincode.dsl.SsmSessionBase
import ssm.sdk.sign.model.SignerAdmin

typealias SsmSessionStartFunction = F2Function<SsmSessionStartCommand, SsmSessionStartResult>

class SsmSessionStartCommand(
	override val baseUrl: String,
	override val channelId: String?,
	override val chaincodeId: String?,
	override val bearerToken: String?,
	val signerAdmin: SignerAdmin,
	val session: SsmSessionBase,
): SsmCommand

class SsmSessionStartResult(
		val invokeReturn: InvokeReturn
): Event