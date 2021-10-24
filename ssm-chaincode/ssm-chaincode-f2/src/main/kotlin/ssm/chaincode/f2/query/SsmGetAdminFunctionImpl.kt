package ssm.chaincode.f2.query

import ssm.chaincode.dsl.config.SsmChaincodeConfig
import ssm.chaincode.dsl.query.SsmGetAdminFunction
import ssm.chaincode.dsl.query.SsmGetAdminResult
import ssm.chaincode.f2.query.commons.ssmF2Function

class SsmGetAdminFunctionImpl {

	fun ssmGetAdminFunction(config: SsmChaincodeConfig): SsmGetAdminFunction = ssmF2Function(config) { cmd, ssmClient ->
		val sessionState = ssmClient.getAdmin(cmd.name)
		SsmGetAdminResult(sessionState)
	}
}
