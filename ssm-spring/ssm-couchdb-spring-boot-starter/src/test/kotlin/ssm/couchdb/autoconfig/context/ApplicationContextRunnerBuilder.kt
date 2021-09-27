package ssm.couchdb.autoconfig.context

import org.springframework.boot.autoconfigure.AutoConfigurations
import org.springframework.boot.test.context.runner.ReactiveWebApplicationContextRunner
import ssm.couchdb.dsl.config.SsmCouchdbConfig


class ApplicationContextRunnerBuilder {

	fun buildContext(config: SsmCouchdbConfig?): ReactiveWebApplicationContextRunner {
		return ReactiveWebApplicationContextRunner()
			.withConfiguration(AutoConfigurations.of(ApplicationContextBuilder.SimpleConfiguration::class.java))
			.withCouchdbConfig(config)
	}

	fun ReactiveWebApplicationContextRunner.withCouchdbConfig(config: SsmCouchdbConfig?): ReactiveWebApplicationContextRunner {
		return config?.let {
			withPropertyValues(
				"ssm.couchdb.url=${config.url}",
				"ssm.couchdb.username=${config.username}",
				"ssm.couchdb.password=${config.password}",
				"ssm.couchdb.serviceName=${config.serviceName}"
			)
		} ?: this
	}

}
