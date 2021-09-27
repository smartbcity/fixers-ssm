package ssm.chaincode.f2.query

import kotlinx.coroutines.future.await
import ssm.chaincode.dsl.SsmChaincodeConfig
import ssm.chaincode.dsl.query.SsmGetQueryFunction
import ssm.chaincode.dsl.query.SsmGetResult
import ssm.chaincode.f2.commons.ssmF2Function

class SsmGetQueryFunctionImpl {

	fun ssmGetQueryFunction(config: SsmChaincodeConfig): SsmGetQueryFunction = ssmF2Function(config) { cmd, ssmClient ->
		val ssm = ssmClient.getSsm(cmd.name).await()
		SsmGetResult(ssm)
	}
}
