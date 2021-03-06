package ssm.dsl.command

import f2.dsl.cqrs.Command
import f2.dsl.cqrs.Event
import f2.dsl.function.F2Function
import f2.dsl.function.F2FunctionRemote
import ssm.dsl.InvokeReturn
import ssm.dsl.SsmSession

typealias SsmStartFunction = F2Function<SsmStartCommand, SsmStartResult>
typealias SsmStartRemoteFunction = F2FunctionRemote<SsmStartCommand, SsmStartResult>

class SsmStartCommand(
	val session: SsmSession
): Command

class SsmStartResult(
	val invokeReturn: InvokeReturn
): Event