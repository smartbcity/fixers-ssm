package ssm.f2.query

import kotlinx.coroutines.future.await
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import ssm.dsl.query.SsmGetAdminFunction
import ssm.dsl.query.SsmGetAdminResult
import ssm.f2.commons.ssmF2Function

@Configuration
class SsmGetAdminFunctionImpl {

	@Bean
	fun ssmGetAdminFunction(): SsmGetAdminFunction = ssmF2Function { cmd, ssmClient ->
		val sessionState = ssmClient.getAdmin(cmd.name).await().orElse( null)
		SsmGetAdminResult(sessionState)
	}

}