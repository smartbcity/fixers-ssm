package ssm.f2

import f2.function.spring.adapter.f2Function
import kotlinx.coroutines.future.await
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import ssm.client.SsmClient
import ssm.client.domain.Signer
import ssm.client.domain.SignerAdmin
import ssm.dsl.function.SSMFunction
import ssm.dsl.function.SsmQueryFunction
import ssm.dsl.query.*

@Configuration
class SsmQueryFunctionImpl(
	private val ssmClient: SsmClient,
) : SsmQueryFunction {

	@Bean
	override fun getSsm(): GetSsmFunction = f2Function { cmd ->
		val ssm = ssmClient.getSsm(cmd.name).await().orElse( null)
		GetSsmResult(ssm )
	}

	@Bean
	override fun getSsmSession(): GetSsmSessionFunction = f2Function { cmd ->
		val sessionState = ssmClient.getSession(cmd.name).await().orElse( null)
		GetSsmSessionResult(sessionState)
	}

	@Bean
	override fun getSsmUser(): GetSsmUserFunction = f2Function { cmd ->
		val sessionState = ssmClient.getAgent(cmd.name).await().orElse( null)
		GetSsmUserResult(sessionState)
	}

	@Bean
	override fun getSsmAdmin(): GetSsmAdminFunction = f2Function { cmd ->
		val sessionState = ssmClient.getAdmin(cmd.name).await().orElse( null)
		GetSsmAdminResult(sessionState)
	}

	@Bean
	override fun getListAdmin(): GetListAdminQueryFunction = f2Function { cmd ->
		val list = ssmClient.listAdmins().await()
		GetListAdminResult(list)
	}

	@Bean
	override fun getListUser(): GetListUserQueryFunction = f2Function { cmd ->
		val list = ssmClient.listAgent().await()
		GetListUserResult(list)
	}

	@Bean
	override fun getListSsm(): GetListSsmQueryFunction = f2Function { cmd ->
		val list = ssmClient.listSsm().await()
		GetListSsmResult(list)
	}

	@Bean
	override fun getListSsmSession(): GetListSsmSessionQueryFunction = f2Function { cmd ->
		val list = ssmClient.listSession().await()
		GetListSsmSessionResult(list)
	}

}