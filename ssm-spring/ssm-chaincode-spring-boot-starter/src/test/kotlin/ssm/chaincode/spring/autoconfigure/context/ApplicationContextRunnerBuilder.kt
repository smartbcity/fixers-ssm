package ssm.chaincode.spring.autoconfigure.context

import org.springframework.boot.autoconfigure.AutoConfigurations
import org.springframework.boot.test.context.runner.ReactiveWebApplicationContextRunner
import ssm.chaincode.dsl.config.SsmChaincodeConfig

class ApplicationContextRunnerBuilder {

	fun buildContext(config: SsmChaincodeConfig?): ReactiveWebApplicationContextRunner {
		return ReactiveWebApplicationContextRunner()
			.withConfiguration(AutoConfigurations.of(ApplicationContextBuilder.SimpleConfiguration::class.java))
			.withConfig(config)
	}

	private fun ReactiveWebApplicationContextRunner.withConfig(config: SsmChaincodeConfig?): ReactiveWebApplicationContextRunner {
		return config?.let {
			withPropertyValues(
				"ssm.chaincode.url=${config.url}",
			)
		} ?: this
	}
}
