package ssm.chaincode.f2.query

import kotlinx.coroutines.future.await
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import ssm.chaincode.dsl.query.SsmGetAdminFunction
import ssm.chaincode.dsl.query.SsmGetAdminResult
import ssm.chaincode.f2.commons.ssmF2Function

@Configuration
class SsmGetAdminFunctionImpl {

	@Bean
	fun ssmGetAdminFunction(): SsmGetAdminFunction = ssmF2Function { cmd, ssmClient ->
		val sessionState = ssmClient.getAdmin(cmd.name).await()
		SsmGetAdminResult(sessionState)
	}
}
