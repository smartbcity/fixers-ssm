package ssm.f2

import f2.function.spring.adapter.f2Function
import kotlinx.coroutines.future.await
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import ssm.client.SsmClient
import ssm.client.SsmClientConfig

@Configuration
class SsmSessionStartFunctionImpl {

	@Bean
	fun ssmSessionStartFunction(): SsmSessionStartFunction = f2Function { cmd ->
		val config = SsmClientConfig(cmd.baseUrl)
		val ssmClient = SsmClient.fromConfig(config)
		val invoke = ssmClient.start(cmd.signerAdmin, cmd.session).await()
		SsmSessionStartResult(invoke)
	}

}