package ssm.f2.query

import f2.function.spring.adapter.f2Function
import kotlinx.coroutines.future.await
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import ssm.client.SsmClient
import ssm.client.SsmClientConfig
import ssm.dsl.query.ListUserQueryFunction
import ssm.dsl.query.GetListUserResult

@Configuration
class ListUserQueryFunctionImpl {

	@Bean
	fun listUserQueryFunction(): ListUserQueryFunction = f2Function { cmd ->
		val config = SsmClientConfig(cmd.baseUrl)
		val ssmClient = SsmClient.fromConfig(config)
		val list = ssmClient.listAgent().await()
		GetListUserResult(list)
	}

}