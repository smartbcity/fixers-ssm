package ssm.f2

import f2.dsl.function.F2Function
import f2.function.spring.adapter.f2Function
import kotlinx.coroutines.future.await
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import ssm.client.SsmClient
import ssm.client.asAgent
import ssm.client.domain.Signer
import ssm.dsl.InvokeReturn
import ssm.dsl.SSMFunction
import ssm.dsl.command.SsmCreateFunction
import ssm.dsl.command.SsmPerformCommand
import ssm.dsl.command.SsmStartCommand
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
	override fun perform(): F2Function<SsmPerformCommand, InvokeReturn> = f2Function { cmd ->
		ssmClient.perform(signer, cmd.action, cmd.context).await()
	}

	@Bean
	override fun start(): F2Function<SsmStartCommand, InvokeReturn> = f2Function { cmd ->
		ssmClient.start(signer, cmd.session).await()
	}

	@Bean
	override fun create(): SsmCreateFunction = f2Function { cmd ->
		initializer.init(signer.asAgent(), cmd.ssm)
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