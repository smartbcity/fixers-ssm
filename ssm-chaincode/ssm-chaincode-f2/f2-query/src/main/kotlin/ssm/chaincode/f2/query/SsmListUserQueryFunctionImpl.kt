package ssm.chaincode.f2.query

import kotlinx.coroutines.future.await
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import ssm.chaincode.dsl.query.SsmListUserQueryFunction
import ssm.chaincode.dsl.query.SsmListUserResult
import ssm.chaincode.f2.commons.ssmF2Function

@Configuration
class SsmListUserQueryFunctionImpl {

	@Bean
	fun ssmListUserQueryFunction(): SsmListUserQueryFunction = ssmF2Function { _, ssmClient ->
		val list = ssmClient.listAgent().await()
		SsmListUserResult(list.toTypedArray())
	}
}
