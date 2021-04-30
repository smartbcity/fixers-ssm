package ssm.f2.query

import kotlinx.coroutines.future.await
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import ssm.dsl.query.SsmListSessionQueryFunction
import ssm.dsl.query.SsmListSessionResult
import ssm.f2.commons.ssmF2Function

@Configuration
class SsmListSessionQueryFunctionImpl {


	@Bean
	fun ssmListSessionQueryFunction(): SsmListSessionQueryFunction = ssmF2Function { _, ssmClient ->
		val list = ssmClient.listSession().await()
		SsmListSessionResult(list)
	}

}