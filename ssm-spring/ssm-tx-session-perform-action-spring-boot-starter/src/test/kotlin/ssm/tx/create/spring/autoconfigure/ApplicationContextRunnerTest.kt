package ssm.tx.create.spring.autoconfigure

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.cloud.function.context.FunctionCatalog
import ssm.chaincode.dsl.config.SsmChaincodeConfig
import ssm.tx.session.spring.autoconfigure.SsmSessionPerformActionAutoConfiguration
import ssm.tx.session.spring.autoconfigure.SsmSessionPerformActionProperties
import ssm.test.spring.ApplicationContextBuilder
import ssm.test.spring.ApplicationContextRunnerBuilder

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


object SsmChaincodeConfigTest {
	val localDockerCompose = SsmChaincodeConfig(
		url = "http://localhost:9090"
	)

	val localDockerComposeParams = mapOf(
		"ssm.chaincode.url" to localDockerCompose.url
	)
}
