package ssm.dsl.command

import f2.dsl.cqrs.Command
import f2.dsl.function.F2Function
import f2.dsl.function.F2FunctionRemote
import ssm.dsl.InvokeReturn
import ssm.dsl.Session

typealias SsmStartFunction = F2Function<SsmStartCommand, InvokeReturn>
typealias SsmStartRemoteFunction = F2FunctionRemote<SsmStartCommand, InvokeReturn>

class SsmStartCommand(
	val session: Session
): Command