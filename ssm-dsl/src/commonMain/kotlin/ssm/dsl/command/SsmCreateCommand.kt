package ssm.dsl.command

import f2.dsl.cqrs.Command
import f2.dsl.function.F2Function
import f2.dsl.function.F2FunctionRemote
import ssm.dsl.Agent
import ssm.dsl.InvokeReturn
import ssm.dsl.Ssm

typealias SsmCreateFunction = F2Function<SsmCreateCommand,  List<InvokeReturn>>
typealias SsmCreateRemoteFunction = F2FunctionRemote<SsmCreateCommand,  List<InvokeReturn>>

class SsmCreateCommand(
	val ssm: Ssm,
	val agent: Agent
) : Command
