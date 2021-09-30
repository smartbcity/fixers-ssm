package ssm.chaincode.f2.query

import ssm.chaincode.dsl.config.SsmChaincodeConfig
import ssm.chaincode.dsl.query.SsmGetUserFunction
import ssm.chaincode.dsl.query.SsmGetUserResult
import ssm.chaincode.f2.commons.ssmF2Function

class SsmGetUserFunctionImpl {

	fun ssmGetUserFunction(config: SsmChaincodeConfig): SsmGetUserFunction = ssmF2Function(config) { cmd, ssmClient ->
		val sessionState = ssmClient.getAgent(cmd.name)
		SsmGetUserResult(sessionState)
	}
}
