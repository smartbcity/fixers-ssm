package ssm.f2

import f2.dsl.cqrs.Command
import f2.dsl.cqrs.Event
import f2.dsl.function.F2Function
import f2.dsl.function.F2FunctionRemote
import ssm.client.sign.model.SignerAdmin
import ssm.dsl.InvokeReturn
import ssm.dsl.Ssm
import ssm.dsl.SsmAgent

typealias SsmCreateFunction = F2Function<SsmCreateCommand, SsmCreateResult>
typealias SsmCreateRemoteFunction = F2FunctionRemote<SsmCreateCommand, SsmCreateResult>

class SsmCreateCommand(
        val baseUrl: String,
        val signerAdmin: SignerAdmin,
        val ssm: Ssm,
        val agent: SsmAgent
) : Command

class SsmCreateResult(
	val invokeReturn: List<InvokeReturn>,
) : Event