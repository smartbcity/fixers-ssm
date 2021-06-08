package ssm.f2

import f2.function.spring.adapter.f2Function
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import ssm.client.SsmClient
import ssm.client.SsmClientConfig

@Configuration
class SsmCreateFunctionImpl {

	@Bean
	fun ssmCreateFunction(): SsmCreateFunction = f2Function { cmd ->
		val config = SsmClientConfig(cmd.baseUrl)
		val ssmClient = SsmClient.fromConfig(config)

		val initializer = SsmInitializer(ssmClient, cmd.signerAdmin)

		val invoke = initializer.init(cmd.agent, cmd.ssmBase)
		SsmCreateResult(invoke)
	}

}