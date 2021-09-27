package ssm.chaincode.f2.query

import kotlinx.coroutines.future.await
import ssm.chaincode.dsl.SsmChaincodeConfig
import ssm.chaincode.dsl.query.SsmListAdminQueryFunction
import ssm.chaincode.dsl.query.SsmListAdminResult
import ssm.chaincode.f2.commons.ssmF2Function

class SsmListAdminQueryFunctionImpl {

	fun ssmListAdminQueryFunction(config: SsmChaincodeConfig): SsmListAdminQueryFunction = ssmF2Function(config) { _, ssmClient ->
		val list = ssmClient.listAdmins().await()
		SsmListAdminResult(list.toTypedArray())
	}
}
