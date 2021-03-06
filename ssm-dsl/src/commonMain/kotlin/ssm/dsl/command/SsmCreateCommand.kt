package ssm.dsl.command

import f2.dsl.cqrs.Command
import f2.dsl.cqrs.Event
import f2.dsl.function.F2Function
import f2.dsl.function.F2FunctionRemote
import ssm.dsl.SsmAgent
import ssm.dsl.InvokeReturn
import ssm.dsl.Ssm

typealias SsmCreateFunction = F2Function<SsmCreateCommand, SsmCreateResult>
typealias SsmCreateRemoteFunction = F2FunctionRemote<SsmCreateCommand, SsmCreateResult>

class SsmCreateCommand(
	val ssm: Ssm,
	val agent: SsmAgent
) : Command

class SsmCreateResult(
	val invokeReturn: List<InvokeReturn>,
) : Event
