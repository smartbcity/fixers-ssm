package ssm.f2

import f2.dsl.cqrs.Command
import f2.dsl.cqrs.Event
import f2.dsl.function.F2Function
import f2.dsl.function.F2FunctionRemote
import ssm.client.domain.Signer
import ssm.dsl.InvokeReturn
import ssm.dsl.SsmContext

typealias SsmSessionPerformActionFunction = F2Function<SsmSessionPerformActionCommand, SsmSessionPerformActionResult>
typealias SsmSessionPerformActionRemoteFunction = F2FunctionRemote<SsmSessionPerformActionCommand, SsmSessionPerformActionResult>

class SsmSessionPerformActionCommand(
	val baseUrl: String,
	val signer: Signer,
	val action: String,
	val context: SsmContext
): Command


class SsmSessionPerformActionResult(
	val invokeReturn: InvokeReturn,
): Event