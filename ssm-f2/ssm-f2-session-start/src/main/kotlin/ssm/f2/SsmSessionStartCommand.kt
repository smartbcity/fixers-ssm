package ssm.f2

import f2.dsl.cqrs.Command
import f2.dsl.cqrs.Event
import f2.dsl.function.F2Function
import ssm.client.sign.model.SignerAdmin
import ssm.dsl.InvokeReturn
import ssm.dsl.SsmSession

typealias SsmSessionStartFunction = F2Function<SsmSessionStartCommand, SsmSessionStartResult>

class SsmSessionStartCommand(
		val baseUrl: String,
		val signerAdmin: SignerAdmin,
		val session: SsmSession,
): Command

class SsmSessionStartResult(
	val invokeReturn: InvokeReturn
): Event