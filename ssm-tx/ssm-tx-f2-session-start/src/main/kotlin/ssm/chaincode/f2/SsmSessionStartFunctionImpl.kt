package ssm.chaincode.f2

import kotlinx.coroutines.future.await
import ssm.chaincode.dsl.config.SsmChaincodeConfig
import ssm.chaincode.f2.commons.ssmF2Function

class SsmSessionStartFunctionImpl {

	fun ssmSessionStartFunction(config: SsmChaincodeConfig): SsmSessionStartFunction = ssmF2Function(config) { cmd, ssmClient ->
		val invoke = ssmClient.start(cmd.signerAdmin, cmd.session).await()
		SsmSessionStartResult(invoke)
	}
}
