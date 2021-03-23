package ssm.f2.query

import f2.function.spring.adapter.f2Function
import kotlinx.coroutines.future.await
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import ssm.client.SsmClient
import ssm.client.SsmClientConfig
import ssm.dsl.query.*

@Configuration
class ListAdminQueryFunctionImpl {

	@Bean
	fun listAdminQueryFunction(): ListAdminQueryFunction = f2Function { cmd ->
		val config = SsmClientConfig(cmd.baseUrl)
		val ssmClient = SsmClient.fromConfig(config)
		val list = ssmClient.listAdmins().await()
		GetListAdminResult(list)
	}

}