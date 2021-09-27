package ssm.chaincode.autoconfig.features

import io.cucumber.java8.En
import org.assertj.core.api.Assertions.assertThat
import org.springframework.boot.test.context.runner.ReactiveWebApplicationContextRunner
import ssm.couchdb.autoconfig.SsmTxConfigTest
import ssm.chaincode.autoconfig.context.ApplicationContextRunnerBuilder


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
