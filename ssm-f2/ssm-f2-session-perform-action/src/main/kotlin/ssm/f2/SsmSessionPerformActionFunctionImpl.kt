package ssm.f2

import kotlinx.coroutines.future.await
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import ssm.f2.commons.ssmF2Function

@Configuration
class SsmSessionPerformActionFunctionImpl {

	@Bean
	fun ssmSessionPerformActionFunction(): SsmSessionPerformActionFunction = ssmF2Function { cmd, ssmClient ->
		val invoke = ssmClient.perform(cmd.signer, cmd.action, cmd.context).await()
		SsmSessionPerformActionResult(invoke)
	}

}