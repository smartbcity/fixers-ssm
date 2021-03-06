package ssm.dsl.command

import f2.dsl.cqrs.Command
import f2.dsl.cqrs.Event
import f2.dsl.function.F2Function
import f2.dsl.function.F2FunctionRemote
import ssm.dsl.SsmAgent
import ssm.dsl.InvokeReturn

typealias SsmRegisterFunction = F2Function<SsmRegisterCommand, SsmRegisterResult>
typealias SsmRegisterRemoteFunction = F2FunctionRemote<SsmRegisterCommand, SsmRegisterResult>

class SsmRegisterCommand(
	val agent: SsmAgent
): Command

class SsmRegisterResult(
	val invokeReturn: InvokeReturn
): Event