package ssm.f2.query

import kotlinx.coroutines.future.await
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import ssm.dsl.query.SsmGetSessionQueryFunction
import ssm.dsl.query.SsmGetSessionResult
import ssm.f2.commons.ssmF2Function

@Configuration
class SsmGetSessionFunctionImpl {

	@Bean
	fun ssmGetSessionQueryFunction(): SsmGetSessionQueryFunction = ssmF2Function { cmd, ssmClient ->
		val sessionState = ssmClient.getSession(cmd.name).await().orElse(null)
		SsmGetSessionResult(sessionState)
	}

}