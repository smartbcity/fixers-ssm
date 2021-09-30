package ssm.chaincode.f2

import kotlinx.coroutines.future.await
import ssm.chaincode.dsl.config.SsmChaincodeConfig
import ssm.chaincode.f2.commons.ssmF2Function

class SsmSessionPerformActionFunctionImpl {

	fun ssmSessionPerformActionFunction(
		config: SsmChaincodeConfig
	): SsmSessionPerformActionFunction = ssmF2Function(config) { cmd, ssmClient ->
		val invoke = ssmClient.perform(cmd.signer, cmd.action, cmd.context)!!
		SsmSessionPerformActionResult(invoke)
	}
}
