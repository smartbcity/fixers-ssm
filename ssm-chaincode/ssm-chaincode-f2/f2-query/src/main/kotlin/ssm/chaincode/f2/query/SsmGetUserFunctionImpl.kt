package ssm.chaincode.f2.query

import kotlinx.coroutines.future.await
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import ssm.chaincode.dsl.query.SsmGetUserFunction
import ssm.chaincode.dsl.query.SsmGetUserResult
import ssm.chaincode.f2.commons.ssmF2Function

@Configuration
class SsmGetUserFunctionImpl {

	@Bean
	fun ssmGetUserFunction(): SsmGetUserFunction = ssmF2Function { cmd, ssmClient ->
		val sessionState = ssmClient.getAgent(cmd.name).await()
		SsmGetUserResult(sessionState)
	}

}