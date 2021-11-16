package ssm.chaincode.spring.autoconfigure

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.cloud.function.context.FunctionCatalog
import ssm.bdd.spring.autoconfigure.ApplicationContextBuilder
import ssm.bdd.spring.autoconfigure.ApplicationContextRunnerBuilder
import ssm.chaincode.dsl.config.ChaincodeSsmConfig

class SsmChaincodeApplicationContextRunnerTest {

	@Test
	fun `spring context runner must must start`() {
		ApplicationContextRunnerBuilder()
			.buildContext(SsmChaincodeConfigTest.localDockerComposeParams).run { context ->
				assertThat(context).hasSingleBean(FunctionCatalog::class.java)
				assertThat(context).hasSingleBean(SsmChaincodeProperties::class.java)
				assertThat(context).hasBean(SsmChaincodeF2AutoConfiguration::ssmGetAdminFunction.name)
				assertThat(context).hasBean(SsmChaincodeF2AutoConfiguration::ssmGetSessionLogsQueryFunction.name)
				assertThat(context).hasBean(SsmChaincodeF2AutoConfiguration::ssmGetSessionLogsQueryFunction.name)
				assertThat(context).hasBean(SsmChaincodeF2AutoConfiguration::ssmGetSessionQueryFunction.name)
				assertThat(context).hasBean(SsmChaincodeF2AutoConfiguration::ssmGetTransactionQueryFunction.name)
				assertThat(context).hasBean(SsmChaincodeF2AutoConfiguration::ssmGetUserFunction.name)
				assertThat(context).hasBean(SsmChaincodeF2AutoConfiguration::ssmListAdminQueryFunction.name)
				assertThat(context).hasBean(SsmChaincodeF2AutoConfiguration::ssmListSessionQueryFunction.name)
				assertThat(context).hasBean(SsmChaincodeF2AutoConfiguration::ssmListSsmQueryFunction.name)
				assertThat(context).hasBean(SsmChaincodeF2AutoConfiguration::ssmListUserQueryFunction.name)
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
		val localDockerCompose = ChaincodeSsmConfig(
			url = "http://localhost:9090"
		)

		val localDockerComposeParams = mapOf(
			"ssm.chaincode.url" to localDockerCompose.url
		)
	}

}
