package ssm.tx.spring.autoconfigure

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.cloud.function.context.FunctionCatalog
import ssm.test.cucumber.spring.ApplicationContextBuilder
import ssm.test.cucumber.spring.ApplicationContextRunnerBuilder

class TxApplicationContextRunnerTest {

	@Test
	fun `spring context runner must must start`() {
		ApplicationContextRunnerBuilder()
			.buildContext(TestConfiguration.localDockerComposeParams).run { context ->
			assertThat(context).hasSingleBean(FunctionCatalog::class.java)
			assertThat(context).hasSingleBean(SsmTxProperties::class.java)
			assertThat(context).hasBean(SsmTxAutoConfiguration::txSsmListQueryFunction.name)
				assertThat(context).hasBean(SsmTxAutoConfiguration::txSsmGetQueryFunction.name)
				assertThat(context).hasBean(SsmTxAutoConfiguration::txSsmSessionListQueryFunction.name)
				assertThat(context).hasBean(SsmTxAutoConfiguration::txSsmSessionGetQueryFunction.name)
				assertThat(context).hasBean(SsmTxAutoConfiguration::txSsmSessionLogListQueryFunction.name)
				assertThat(context).hasBean(SsmTxAutoConfiguration::txSsmSessionLogGetQueryFunction.name)
		}
	}

	@Test
	fun `spring context must must start`() {
		val context = ApplicationContextBuilder().create(
			types = arrayOf(ApplicationContextBuilder.SimpleConfiguration::class.java),
			config = TestConfiguration.localDockerComposeParams
		)
		assertThat(context.getBean(SsmTxAutoConfiguration::ssmTxConfig.name)).isNotNull
		assertThat(context.getBean(FunctionCatalog::class.java)).isNotNull
	}
}
