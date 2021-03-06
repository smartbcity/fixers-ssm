package ssm.dsl.command

import f2.dsl.cqrs.Command
import f2.dsl.cqrs.Event
import f2.dsl.function.F2Function
import f2.dsl.function.F2FunctionRemote
import ssm.dsl.SsmContext
import ssm.dsl.InvokeReturn

typealias SsmPerformFunction = F2Function<SsmPerformCommand, SsmPerformResult>
typealias SsmPerformRemoteFunction = F2FunctionRemote<SsmPerformCommand, SsmPerformResult>

class SsmPerformCommand(
	val action: String,
	val context: SsmContext
): Command


class SsmPerformResult(
	val invokeReturn: InvokeReturn,
): Event