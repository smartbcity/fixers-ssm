package ssm.couchdb.autoconfig.features

import io.cucumber.java8.En
import org.assertj.core.api.Assertions.assertThat
import org.springframework.boot.test.context.runner.ReactiveWebApplicationContextRunner
import ssm.couchdb.autoconfig.SsmTxConfigTest
import ssm.couchdb.autoconfig.context.ApplicationContextRunnerBuilder
import ssm.couchdb.dsl.config.SsmCouchdbConfig


class CatalogueSteps: En {
	var contextBuilder: ReactiveWebApplicationContextRunner? = null

	init {
		When("I get a valid spring application context") {
			contextBuilder = ApplicationContextRunnerBuilder().buildContext(SsmTxConfigTest.localDockerCompose)
		}
		Then("Instance of {string} is an injectable bean") { functionName: String ->
			contextBuilder?.run { context ->
				assertThat(context).hasBean(functionName)
			}
		}
	}
}
