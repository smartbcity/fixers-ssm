package ssm.chaincode.f2.query

import ssm.chaincode.dsl.config.SsmChaincodeConfig
import ssm.chaincode.dsl.query.SsmListAdminQueryFunction
import ssm.chaincode.dsl.query.SsmListAdminResult
import ssm.chaincode.f2.commons.ssmF2Function

class SsmListAdminQueryFunctionImpl {

	fun ssmListAdminQueryFunction(config: SsmChaincodeConfig): SsmListAdminQueryFunction = ssmF2Function(config) { _, ssmClient ->
		val list = ssmClient.listAdmins()
		SsmListAdminResult(list.toTypedArray())
	}
}
