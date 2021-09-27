package ssm.chaincode.f2.query

import kotlinx.coroutines.future.await
import ssm.chaincode.dsl.config.SsmChaincodeConfig
import ssm.chaincode.dsl.query.SsmGetSessionQueryFunction
import ssm.chaincode.dsl.query.SsmGetSessionResult
import ssm.chaincode.f2.commons.ssmF2Function

class SsmGetSessionQueryFunctionImpl {

	fun ssmGetSessionQueryFunction(config: SsmChaincodeConfig): SsmGetSessionQueryFunction = ssmF2Function(config) { cmd, ssmClient ->
		val sessionState = ssmClient.getSession(cmd.name).await()
		SsmGetSessionResult(sessionState)
	}
}
