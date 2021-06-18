package ssm.chaincode.f2.query

import kotlinx.coroutines.future.await
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import ssm.chaincode.dsl.query.SsmListSessionQueryFunction
import ssm.chaincode.dsl.query.SsmListSessionResult
import ssm.chaincode.f2.commons.ssmF2Function

@Configuration
class SsmListSessionQueryFunctionImpl {


	@Bean
	fun ssmListSessionQueryFunction(): SsmListSessionQueryFunction = ssmF2Function { _, ssmClient ->
		val list = ssmClient.listSession().await()
		SsmListSessionResult(list.toTypedArray())
	}

}