package ssm.f2

import f2.dsl.cqrs.Event
import f2.dsl.function.F2Function
import f2.dsl.function.F2FunctionRemote
import ssm.client.sign.model.Signer
import ssm.dsl.InvokeReturn
import ssm.dsl.SsmCommand
import ssm.dsl.SsmContextBase

typealias SsmSessionPerformActionFunction = F2Function<SsmSessionPerformActionCommand, SsmSessionPerformActionResult>
typealias SsmSessionPerformActionRemoteFunction = F2FunctionRemote<SsmSessionPerformActionCommand, SsmSessionPerformActionResult>

class SsmSessionPerformActionCommand(
		override val baseUrl: String,
		override val channelId: String?,
		override val chaincodeId: String?,
		override val bearerToken: String?,
		val signer: Signer,
		val action: String,
		val context: SsmContextBase
): SsmCommand


class SsmSessionPerformActionResult(
		val invokeReturn: InvokeReturn,
): Event