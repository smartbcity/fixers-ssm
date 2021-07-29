package ssm.chaincode.f2

import f2.dsl.cqrs.Event
import f2.dsl.fnc.F2Function
import ssm.chaincode.dsl.InvokeReturn
import ssm.chaincode.dsl.SsmCommandDTO
import ssm.chaincode.dsl.SsmContext
import ssm.sdk.sign.model.Signer

typealias SsmSessionPerformActionFunction = F2Function<SsmSessionPerformActionCommand, SsmSessionPerformActionResult>

class SsmSessionPerformActionCommand(
	override val baseUrl: String,
	override val channelId: String?,
	override val chaincodeId: String?,
	override val bearerToken: String?,
	val signer: Signer,
	val action: String,
	val context: SsmContext
): SsmCommandDTO


class SsmSessionPerformActionResult(
	val invokeReturn: InvokeReturn,
): Event