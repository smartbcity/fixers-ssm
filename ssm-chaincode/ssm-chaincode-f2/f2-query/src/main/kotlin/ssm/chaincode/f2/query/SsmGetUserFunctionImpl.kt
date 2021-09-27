package ssm.chaincode.f2.query

import kotlinx.coroutines.future.await
import ssm.chaincode.dsl.SsmChaincodeConfig
import ssm.chaincode.dsl.query.SsmGetUserFunction
import ssm.chaincode.dsl.query.SsmGetUserResult
import ssm.chaincode.f2.commons.ssmF2Function

class SsmGetUserFunctionImpl {

	fun ssmGetUserFunction(config: SsmChaincodeConfig): SsmGetUserFunction = ssmF2Function(config) { cmd, ssmClient ->
		val sessionState = ssmClient.getAgent(cmd.name).await()
		SsmGetUserResult(sessionState)
	}
}
