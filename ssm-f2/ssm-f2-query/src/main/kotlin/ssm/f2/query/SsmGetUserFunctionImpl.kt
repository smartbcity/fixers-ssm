package ssm.f2.query

import kotlinx.coroutines.future.await
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import ssm.dsl.query.SsmGetUserFunction
import ssm.dsl.query.SsmGetUserResult
import ssm.f2.commons.ssmF2Function

@Configuration
class SsmGetUserFunctionImpl {

	@Bean
	fun ssmGetUserFunction(): SsmGetUserFunction = ssmF2Function { cmd, ssmClient ->
		val sessionState = ssmClient.getAgent(cmd.name).await().orElse( null)
		SsmGetUserResult(sessionState)
	}

}