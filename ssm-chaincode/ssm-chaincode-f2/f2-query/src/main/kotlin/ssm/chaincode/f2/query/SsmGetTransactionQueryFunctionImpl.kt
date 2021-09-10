package ssm.chaincode.f2.query

import kotlinx.coroutines.future.await
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Service
import ssm.chaincode.dsl.query.SsmGetTransactionQueryFunction
import ssm.chaincode.dsl.query.SsmGetTransactionQueryResult
import ssm.chaincode.f2.commons.ssmF2Function

@Service
class SsmGetTransactionQueryFunctionImpl {

	@Bean
	fun ssmGetTransactionQueryFunction(): SsmGetTransactionQueryFunction = ssmF2Function { cmd, ssmClient ->
		ssmClient.getTransaction(cmd.id)
			.await()
			.let(::SsmGetTransactionQueryResult)
	}
}
