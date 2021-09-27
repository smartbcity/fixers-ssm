package ssm.chaincode.f2

import ssm.chaincode.dsl.SsmChaincodeConfig
import ssm.chaincode.f2.commons.ssmF2Function

class SsmCreateFunctionImpl {

	fun ssmCreateFunction(config: SsmChaincodeConfig): SsmCreateFunction = ssmF2Function(config) { cmd, ssmClient ->
		val initializer = SsmInitializer(ssmClient, cmd.signerAdmin)
		val invoke = initializer.init(cmd.agent, cmd.ssm)
		SsmCreateResult(invoke)
	}
}
