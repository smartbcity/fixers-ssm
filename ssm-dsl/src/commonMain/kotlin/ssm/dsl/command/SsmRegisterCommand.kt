package ssm.dsl.command

import f2.dsl.cqrs.Command
import f2.dsl.function.F2Function
import f2.dsl.function.F2FunctionRemote
import ssm.dsl.Agent
import ssm.dsl.InvokeReturn

typealias SsmRegisterFunction = F2Function<SsmPerformCommand, InvokeReturn>
typealias SsmRegisterRemoteFunction = F2FunctionRemote<SsmPerformCommand, InvokeReturn>

class SsmRegisterCommand(
	val agent: Agent
): Command