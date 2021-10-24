package ssm.chaincode.f2.query

import ssm.chaincode.dsl.config.SsmChaincodeConfig
import ssm.chaincode.dsl.query.SsmGetTransactionQueryFunction
import ssm.chaincode.dsl.query.SsmGetTransactionQueryResult
import ssm.chaincode.f2.query.commons.ssmF2Function

class SsmGetTransactionQueryFunctionImpl {

	fun ssmGetTransactionQueryFunction(config: SsmChaincodeConfig): SsmGetTransactionQueryFunction = ssmF2Function(config) { cmd, ssmClient ->
		ssmClient.getTransaction(cmd.id)
			.let(::SsmGetTransactionQueryResult)
	}
}
