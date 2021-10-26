package ssm.chaincode.spring.autoconfigure

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.cloud.function.context.FunctionCatalog
import ssm.bdd.spring.autoconfigure.ApplicationContextBuilder
import ssm.chaincode.dsl.config.SsmChaincodeConfig
import ssm.bdd.spring.autoconfigure.ApplicationContextRunnerBuilder

class SsmChaincodeApplicationContextRunnerTest {

	@Test
	fun `spring context runner must must start`() {
		ApplicationContextRunnerBuilder()
			.buildContext(SsmChaincodeConfigTest.localDockerComposeParams).run { context ->
				assertThat(context).hasSingleBean(FunctionCatalog::class.java)
				assertThat(context).hasSingleBean(SsmChaincodeProperties::class.java)
				assertThat(context).hasBean(SsmChaincodeAutoConfiguration::ssmGetAdminFunction.name)
				assertThat(context).hasBean(SsmChaincodeAutoConfiguration::ssmGetSessionLogsQueryFunction.name)
				assertThat(context).hasBean(SsmChaincodeAutoConfiguration::ssmGetSessionLogsQueryFunction.name)
				assertThat(context).hasBean(SsmChaincodeAutoConfiguration::ssmGetSessionQueryFunction.name)
				assertThat(context).hasBean(SsmChaincodeAutoConfiguration::ssmGetTransactionQueryFunction.name)
				assertThat(context).hasBean(SsmChaincodeAutoConfiguration::ssmGetUserFunction.name)
				assertThat(context).hasBean(SsmChaincodeAutoConfiguration::ssmListAdminQueryFunction.name)
				assertThat(context).hasBean(SsmChaincodeAutoConfiguration::ssmListSessionQueryFunction.name)
				assertThat(context).hasBean(SsmChaincodeAutoConfiguration::ssmListSsmQueryFunction.name)
				assertThat(context).hasBean(SsmChaincodeAutoConfiguration::ssmListUserQueryFunction.name)
			}
	}

	@Test
	fun `spring context must must start`() {
		val context = ApplicationContextBuilder().create(
			types = arrayOf(ApplicationContextBuilder.SimpleConfiguration::class.java),
			config = SsmChaincodeConfigTest.localDockerComposeParams
		)
		assertThat(context.getBean(SsmChaincodeAutoConfiguration::ssmChaincodeConfig.name)).isNotNull
		assertThat(context.getBean(FunctionCatalog::class.java)).isNotNull
	}


	object SsmChaincodeConfigTest {
		val localDockerCompose = SsmChaincodeConfig(
			url = "http://localhost:9090"
		)

		val localDockerComposeParams = mapOf(
			"ssm.chaincode.url" to localDockerCompose.url
		)
	}

}
