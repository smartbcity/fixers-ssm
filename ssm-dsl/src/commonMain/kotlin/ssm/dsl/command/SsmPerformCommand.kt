package ssm.dsl.command

import f2.dsl.cqrs.Command
import f2.dsl.function.F2Function
import f2.dsl.function.F2FunctionRemote
import ssm.dsl.Context
import ssm.dsl.InvokeReturn

typealias SsmPerformFunction = F2Function<SsmPerformCommand, InvokeReturn>
typealias SsmPerformRemoteFunction = F2FunctionRemote<SsmPerformCommand, InvokeReturn>

class SsmPerformCommand(
	val action: String,
	val context: Context
): Command