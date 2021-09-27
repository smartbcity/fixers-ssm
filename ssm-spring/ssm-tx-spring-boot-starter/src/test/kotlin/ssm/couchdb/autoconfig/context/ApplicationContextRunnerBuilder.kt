package ssm.couchdb.autoconfig.context

import org.springframework.boot.autoconfigure.AutoConfigurations
import org.springframework.boot.test.context.runner.ReactiveWebApplicationContextRunner
import ssm.tx.dsl.config.SsmTxConfig


class ApplicationContextRunnerBuilder {

	fun buildContext(config: SsmTxConfig?): ReactiveWebApplicationContextRunner {
		return ReactiveWebApplicationContextRunner()
			.withConfiguration(AutoConfigurations.of(ApplicationContextBuilder.SimpleConfiguration::class.java))
			.withConfig(config)
	}

	private fun ReactiveWebApplicationContextRunner.withConfig(config: SsmTxConfig?): ReactiveWebApplicationContextRunner {
		return config?.let {
			withPropertyValues(
				"ssm.tx.chaincode.url=${config.chaincode.url}",
				"ssm.tx.couchdb.url=${config.couchdb.url}",
				"ssm.tx.couchdb.username=${config.couchdb.username}",
				"ssm.tx.couchdb.password=${config.couchdb.password}",
				"ssm.tx.couchdb.serviceName=${config.couchdb.serviceName}"
			)
		} ?: this
	}

}
