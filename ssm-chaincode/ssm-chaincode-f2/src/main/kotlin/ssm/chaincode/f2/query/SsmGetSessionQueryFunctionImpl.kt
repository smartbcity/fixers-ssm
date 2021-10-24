package ssm.chaincode.f2.query

import ssm.chaincode.dsl.config.SsmChaincodeConfig
import ssm.chaincode.dsl.query.SsmGetSessionQueryFunction
import ssm.chaincode.dsl.query.SsmGetSessionResult
import ssm.chaincode.f2.query.commons.ssmF2Function

class SsmGetSessionQueryFunctionImpl {

	fun ssmGetSessionQueryFunction(config: SsmChaincodeConfig): SsmGetSessionQueryFunction = ssmF2Function(config) { cmd, ssmClient ->
		val sessionState = ssmClient.getSession(cmd.sessionName)
		SsmGetSessionResult(sessionState)
	}
}
