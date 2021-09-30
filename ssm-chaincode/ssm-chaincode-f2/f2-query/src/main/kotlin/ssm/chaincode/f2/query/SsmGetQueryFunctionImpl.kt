package ssm.chaincode.f2.query

import ssm.chaincode.dsl.config.SsmChaincodeConfig
import ssm.chaincode.dsl.query.SsmGetQueryFunction
import ssm.chaincode.dsl.query.SsmGetResult
import ssm.chaincode.f2.commons.ssmF2Function

class SsmGetQueryFunctionImpl {

	fun ssmGetQueryFunction(config: SsmChaincodeConfig): SsmGetQueryFunction = ssmF2Function(config) { cmd, ssmClient ->
		val ssm = ssmClient.getSsm(cmd.name)
		SsmGetResult(ssm)
	}
}
