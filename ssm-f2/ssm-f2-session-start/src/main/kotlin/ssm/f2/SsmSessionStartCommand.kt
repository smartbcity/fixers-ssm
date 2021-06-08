package ssm.f2

import f2.dsl.cqrs.Event
import f2.dsl.function.F2Function
import ssm.client.sign.model.SignerAdmin
import ssm.dsl.InvokeReturn
import ssm.dsl.SsmCommand
import ssm.dsl.SsmSessionBase

typealias SsmSessionStartFunction = F2Function<SsmSessionStartCommand, SsmSessionStartResult>

class SsmSessionStartCommand(
		override val baseUrl: String,
		override val channelId: String?,
		override val chaincodeId: String?,
		override val bearerToken: String?,
		val signerAdmin: SignerAdmin,
		val session: SsmSessionBase,
): SsmCommand
    val baseUrl: String,
    val signerAdmin: SignerAdmin,
    val session: SsmSessionBase,
): Command

class SsmSessionStartResult(
		val invokeReturn: InvokeReturn
): Event