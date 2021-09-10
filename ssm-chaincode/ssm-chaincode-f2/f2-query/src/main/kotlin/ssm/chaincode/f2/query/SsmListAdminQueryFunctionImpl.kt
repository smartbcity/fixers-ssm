package ssm.chaincode.f2.query

import kotlinx.coroutines.future.await
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import ssm.chaincode.dsl.query.SsmListAdminQueryFunction
import ssm.chaincode.dsl.query.SsmListAdminResult
import ssm.chaincode.f2.commons.ssmF2Function

@Configuration
class SsmListAdminQueryFunctionImpl {

	@Bean
	fun ssmListAdminQueryFunction(): SsmListAdminQueryFunction = ssmF2Function { _, ssmClient ->
		val list = ssmClient.listAdmins().await()
		SsmListAdminResult(list.toTypedArray())
	}
}
