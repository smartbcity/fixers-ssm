package ssm.chaincode.f2.query

import kotlinx.coroutines.future.await
import ssm.chaincode.dsl.config.SsmChaincodeConfig
import ssm.chaincode.dsl.query.SsmListSsmQueryFunction
import ssm.chaincode.dsl.query.SsmListSsmResult
import ssm.chaincode.f2.commons.ssmF2Function

class SsmListSsmQueryFunctionImpl {

	fun ssmListSsmQueryFunction(config: SsmChaincodeConfig): SsmListSsmQueryFunction = ssmF2Function(config) { _, ssmClient ->
		val list = ssmClient.listSsm().await()
		SsmListSsmResult(list.toTypedArray())
	}
}
