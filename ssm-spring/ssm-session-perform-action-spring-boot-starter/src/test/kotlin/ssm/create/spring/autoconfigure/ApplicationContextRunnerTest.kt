package ssm.create.spring.autoconfigure

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.cloud.function.context.FunctionCatalog
import ssm.session.spring.autoconfigure.SsmSessionPerformActionAutoConfiguration
import ssm.session.spring.autoconfigure.SsmSessionPerformActionProperties
import ssm.test.cucumber.spring.ApplicationContextBuilder
import ssm.test.cucumber.spring.ApplicationContextRunnerBuilder

class ApplicationContextRunnerTest {

	@Test
	fun `spring context runner must must start`() {
		ApplicationContextRunnerBuilder()
			.buildContext(SsmChaincodeConfigTest.localDockerComposeParams).run { context ->
				assertThat(context).hasSingleBean(FunctionCatalog::class.java)
				assertThat(context).hasSingleBean(SsmSessionPerformActionProperties::class.java)
				assertThat(context).hasBean(SsmSessionPerformActionAutoConfiguration::ssmSessionPerformActionFunction.name)
			}
	}


	@Test
	fun `spring context must must start`() {
		val context = ApplicationContextBuilder().create(
			types = arrayOf(ApplicationContextBuilder.SimpleConfiguration::class.java),
			config = SsmChaincodeConfigTest.localDockerComposeParams
		)
		assertThat(context.getBean(SsmSessionPerformActionAutoConfiguration::ssmSessionPerformActionFunction.name)).isNotNull
		assertThat(context.getBean(FunctionCatalog::class.java)).isNotNull
	}
}
