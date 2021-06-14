package ssm.chaincode.f2.query

import kotlinx.coroutines.future.await
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import ssm.chaincode.dsl.query.SsmListSsmQueryFunction
import ssm.chaincode.dsl.query.SsmListSsmResult
import ssm.chaincode.f2.commons.ssmF2Function

@Configuration
class SsmListSsmQueryFunctionImpl {

	@Bean
	fun ssmListSsmQueryFunction(): SsmListSsmQueryFunction = ssmF2Function { _, ssmClient ->
		val list = ssmClient.listSsm().await()
		SsmListSsmResult(list.toTypedArray())
	}

}