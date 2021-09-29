package ssm.data.spring.autoconfigure.features

import io.cucumber.java8.En
import org.assertj.core.api.Assertions.assertThat
import org.springframework.boot.test.context.runner.ReactiveWebApplicationContextRunner
import ssm.test.cucumber.spring.ApplicationContextRunnerBuilder
import ssm.data.spring.autoconfigure.TestConfiguration


class CatalogueSteps: En {
	var contextBuilder: ReactiveWebApplicationContextRunner? = null

	init {
		When("I get a valid spring application context") {
			contextBuilder = ApplicationContextRunnerBuilder().buildContext(TestConfiguration.localDockerComposeParams)
		}
		Then("Instance of {string} is an injectable bean") { functionName: String ->
			contextBuilder?.run { context ->
				assertThat(context).hasBean(functionName)
			}
		}

		When("I get an empty spring application context") {
			contextBuilder = ApplicationContextRunnerBuilder().buildContext()
		}

		Then("Instance of {string} is not injectable bean") { functionName: String ->
			contextBuilder?.run { context ->
				assertThat(context).doesNotHaveBean(functionName)
			}
		}
	}
}
