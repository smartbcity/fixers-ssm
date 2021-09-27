package ssm.chaincode.f2.query

import kotlinx.coroutines.future.await
import ssm.chaincode.dsl.config.SsmChaincodeConfig
import ssm.chaincode.dsl.query.SsmListSessionQueryFunction
import ssm.chaincode.dsl.query.SsmListSessionResult
import ssm.chaincode.f2.commons.ssmF2Function

class SsmListSessionQueryFunctionImpl {

	fun ssmListSessionQueryFunction(config: SsmChaincodeConfig): SsmListSessionQueryFunction = ssmF2Function(config) { _, ssmClient ->
		val list = ssmClient.listSession().await()
		SsmListSessionResult(list.toTypedArray())
	}
}
