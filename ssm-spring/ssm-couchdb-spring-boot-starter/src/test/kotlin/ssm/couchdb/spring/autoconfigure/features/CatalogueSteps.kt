package ssm.couchdb.spring.autoconfigure.features

import io.cucumber.java8.En
import org.assertj.core.api.Assertions.assertThat
import org.springframework.boot.test.context.runner.ReactiveWebApplicationContextRunner
import ssm.couchdb.spring.autoconfigure.context.ApplicationContextRunnerBuilder
import ssm.couchdb.dsl.config.SsmCouchdbConfig

class CatalogueSteps: En {

	var contextBuilder: ReactiveWebApplicationContextRunner? = null

	init {
		When("I get a valid spring application context") {
			contextBuilder = ApplicationContextRunnerBuilder().buildContext(SsmCouchdbConfig(
				url = "http://localhost:5000",
				username = "couchdb",
				password = "couchdb",
				serviceName = "ssm-couchdb-test"
			))
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
