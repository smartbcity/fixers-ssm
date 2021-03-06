package ssm.f2

import f2.dsl.function.F2Function
import f2.function.spring.adapter.f2Function
import kotlinx.coroutines.future.await
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import ssm.client.SsmClient
import ssm.client.asAgent
import ssm.client.domain.Signer
import ssm.dsl.SSMFunction
import ssm.dsl.command.*
import ssm.dsl.query.GetSsmFunction
import ssm.dsl.query.GetSsmResult
import ssm.dsl.query.GetSsmSessionFunction
import ssm.dsl.query.GetSsmSessionResult

@Configuration
class SsmFunctionImpl(
	private val ssmClient: SsmClient,
	private val initializer: SsmInitializer,
	private val signer: Signer,
) : SSMFunction {

	@Bean
	override fun perform(): F2Function<SsmPerformCommand, SsmPerformResult> = f2Function { cmd ->
		val invoke = ssmClient.perform(signer, cmd.action, cmd.context).await()
		SsmPerformResult(invoke)
	}

	@Bean
	override fun start(): F2Function<SsmStartCommand, SsmStartResult> = f2Function { cmd ->
		val invoke = ssmClient.start(signer, cmd.session).await()
		SsmStartResult(invoke)
	}

	@Bean
	override fun create(): SsmCreateFunction = f2Function { cmd ->
		val invoke = initializer.init(signer.asAgent(), cmd.ssm)
		SsmCreateResult(invoke)
	}

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

}