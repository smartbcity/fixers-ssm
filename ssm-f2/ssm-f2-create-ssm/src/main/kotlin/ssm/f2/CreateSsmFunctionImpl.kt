package ssm.f2

import f2.function.spring.adapter.f2Function
import kotlinx.coroutines.future.await
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import ssm.client.SsmClient
import ssm.client.SsmClientConfig
import ssm.client.asAgent

@Configuration
class CreateSsmFunctionImpl {

	@Bean
	fun ssmCreateFunction(): SsmCreateFunction = f2Function { cmd ->
		val config = SsmClientConfig(cmd.baseUrl)
		val ssmClient = SsmClient.fromConfig(config)

		val initializer = SsmInitializer(ssmClient, cmd.signerAdmin)

		val invoke = initializer.init(cmd.agent, cmd.ssm)
		SsmCreateResult(invoke)
	}

}