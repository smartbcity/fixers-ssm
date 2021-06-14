package ssm.chaincode.f2

import f2.dsl.cqrs.Event
import f2.dsl.function.F2Function
import f2.dsl.function.F2FunctionRemote
import ssm.chaincode.dsl.InvokeReturn
import ssm.chaincode.dsl.SsmAgentBase
import ssm.chaincode.dsl.SsmBase
import ssm.chaincode.dsl.SsmCommand
import ssm.sdk.sign.model.SignerAdmin

typealias SsmCreateFunction = F2Function<SsmCreateCommand, SsmCreateResult>
typealias SsmCreateRemoteFunction = F2FunctionRemote<SsmCreateCommand, SsmCreateResult>

class SsmCreateCommand(
	override val baseUrl: String,
	override val channelId: String?,
	override val bearerToken: String?,
	val signerAdmin: SignerAdmin,
	val ssm: SsmBase,
	override val chaincodeId: String = ssm.name,
	val agent: SsmAgentBase
): SsmCommand

class SsmCreateResult(
	val invokeReturn: List<InvokeReturn>,
): Event