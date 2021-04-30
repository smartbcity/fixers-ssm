package ssm.f2.query

import kotlinx.coroutines.future.await
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import ssm.dsl.query.SsmListUserQueryFunction
import ssm.dsl.query.SsmListUserResult
import ssm.f2.commons.ssmF2Function

@Configuration
class SsmListUserQueryFunctionImpl {

	@Bean
	fun ssmListUserQueryFunction(): SsmListUserQueryFunction = ssmF2Function { _, ssmClient ->
		val list = ssmClient.listAgent().await()
		SsmListUserResult(list)
	}

}