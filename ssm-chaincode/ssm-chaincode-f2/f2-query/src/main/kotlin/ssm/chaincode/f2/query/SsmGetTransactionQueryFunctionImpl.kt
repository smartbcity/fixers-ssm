package ssm.chaincode.f2.query

import kotlinx.coroutines.future.await
import ssm.chaincode.dsl.config.SsmChaincodeConfig
import ssm.chaincode.dsl.query.SsmGetTransactionQueryFunction
import ssm.chaincode.dsl.query.SsmGetTransactionQueryResult
import ssm.chaincode.f2.commons.ssmF2Function

class SsmGetTransactionQueryFunctionImpl {

	fun ssmGetTransactionQueryFunction(config: SsmChaincodeConfig): SsmGetTransactionQueryFunction = ssmF2Function(config) { cmd, ssmClient ->
		ssmClient.getTransaction(cmd.id)
			.await()
			.let(::SsmGetTransactionQueryResult)
	}
}
