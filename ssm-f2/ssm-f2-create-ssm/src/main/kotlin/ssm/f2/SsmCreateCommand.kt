package ssm.f2

import f2.dsl.cqrs.Command
import f2.dsl.cqrs.Event
import f2.dsl.function.F2Function
import f2.dsl.function.F2FunctionRemote
import ssm.client.domain.SignerAdmin
import ssm.dsl.InvokeReturn
import ssm.dsl.SsmBase
import ssm.dsl.SsmAgentBase

typealias SsmCreateFunction = F2Function<SsmCreateCommand, SsmCreateResult>
typealias SsmCreateRemoteFunction = F2FunctionRemote<SsmCreateCommand, SsmCreateResult>

class SsmCreateCommand(
	val baseUrl: String,
	val signerAdmin: SignerAdmin,
	val ssmBase: SsmBase,
	val agent: SsmAgentBase
) : Command

class SsmCreateResult(
	val invokeReturn: List<InvokeReturn>,
) : Event