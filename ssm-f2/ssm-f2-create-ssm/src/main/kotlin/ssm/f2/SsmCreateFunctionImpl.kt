package ssm.f2

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import ssm.f2.commons.ssmF2Function

@Configuration
class SsmCreateFunctionImpl {

	@Bean
	fun ssmCreateFunction(): SsmCreateFunction = ssmF2Function { cmd, ssmClient ->
		val initializer = SsmInitializer(ssmClient, cmd.signerAdmin)
		val invoke = initializer.init(cmd.agent, cmd.ssm)
		SsmCreateResult(invoke)
	}
}