package ssm.f2.query

import kotlinx.coroutines.future.await
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import ssm.dsl.query.SsmListSsmQueryFunction
import ssm.dsl.query.SsmListSsmResult
import ssm.f2.commons.ssmF2Function

@Configuration
class SsmListSsmQueryFunctionImpl {

	@Bean
	fun ssmListSsmQueryFunction(): SsmListSsmQueryFunction = ssmF2Function { _, ssmClient ->
		val list = ssmClient.listSsm().await()
		SsmListSsmResult(list)
	}

}