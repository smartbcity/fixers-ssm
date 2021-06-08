package ssm.f2.query

import kotlinx.coroutines.future.await
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import ssm.dsl.query.SsmListAdminQueryFunction
import ssm.dsl.query.SsmListAdminResult
import ssm.f2.commons.ssmF2Function

@Configuration
class SsmListAdminQueryFunctionImpl {

	@Bean
	fun ssmListAdminQueryFunction(): SsmListAdminQueryFunction = ssmF2Function { _, ssmClient ->
		val list = ssmClient.listAdmins().await()
		SsmListAdminResult(list.toTypedArray())
	}

}