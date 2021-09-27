package ssm.tx.spring.autoconfigure.features

import io.cucumber.java8.En
import org.assertj.core.api.Assertions.assertThat
import org.springframework.boot.test.context.runner.ReactiveWebApplicationContextRunner
import ssm.tx.spring.autoconfigure.SsmTxConfigTest
import ssm.tx.spring.autoconfigure.context.ApplicationContextRunnerBuilder


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

		When("I get an empty spring application context") {
			contextBuilder = ApplicationContextRunnerBuilder().buildContext(null)
		}

		Then("Instance of {string} is not injectable bean") { functionName: String ->
			contextBuilder?.run { context ->
				assertThat(context).doesNotHaveBean(functionName)
			}
		}
	}
}
