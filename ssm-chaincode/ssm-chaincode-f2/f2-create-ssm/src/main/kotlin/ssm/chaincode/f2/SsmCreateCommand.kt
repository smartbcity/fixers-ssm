package ssm.chaincode.f2

import f2.dsl.cqrs.Event
import f2.dsl.fnc.F2Function
import ssm.chaincode.dsl.InvokeReturn
import ssm.chaincode.dsl.SsmAgent
import ssm.chaincode.dsl.Ssm
import ssm.chaincode.dsl.SsmCommandDTO
import ssm.sdk.sign.model.SignerAdmin

typealias SsmCreateFunction = F2Function<SsmCreateCommand, SsmCreateResult>

class SsmCreateCommand(
	override val baseUrl: String,
	override val channelId: String?,
	override val bearerToken: String?,
	val signerAdmin: SignerAdmin,
	val ssm: Ssm,
	override val chaincodeId: String = ssm.name,
	val agent: SsmAgent,
) : SsmCommandDTO

class SsmCreateResult(
	val invokeReturn: List<InvokeReturn>,
) : Event