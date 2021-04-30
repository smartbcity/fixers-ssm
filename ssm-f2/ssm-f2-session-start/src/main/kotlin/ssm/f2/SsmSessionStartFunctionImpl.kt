package ssm.f2

import kotlinx.coroutines.future.await
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import ssm.f2.commons.ssmF2Function

@Configuration
class SsmSessionStartFunctionImpl {

	@Bean
	fun ssmSessionStartFunction(): SsmSessionStartFunction = ssmF2Function { cmd, ssmClient ->
		val invoke = ssmClient.start(cmd.signerAdmin, cmd.session).await()
		SsmSessionStartResult(invoke)
	}

}