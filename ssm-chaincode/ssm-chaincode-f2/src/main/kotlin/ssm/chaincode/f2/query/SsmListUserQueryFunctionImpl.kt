package ssm.chaincode.f2.query

import ssm.chaincode.dsl.config.SsmChaincodeConfig
import ssm.chaincode.dsl.query.SsmListUserQueryFunction
import ssm.chaincode.dsl.query.SsmListUserResult
import ssm.chaincode.f2.query.commons.ssmF2Function

class SsmListUserQueryFunctionImpl {

	fun ssmListUserQueryFunction(config: SsmChaincodeConfig): SsmListUserQueryFunction = ssmF2Function(config) { _, ssmClient ->
		val list = ssmClient.listUsers()
		SsmListUserResult(list.toTypedArray())
	}
}
