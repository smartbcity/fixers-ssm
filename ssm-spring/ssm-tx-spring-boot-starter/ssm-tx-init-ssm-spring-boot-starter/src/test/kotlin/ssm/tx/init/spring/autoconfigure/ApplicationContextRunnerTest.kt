package ssm.tx.init.spring.autoconfigure

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.cloud.function.context.FunctionCatalog
import ssm.bdd.spring.autoconfigure.ApplicationContextBuilder
import ssm.bdd.spring.autoconfigure.ApplicationContextRunnerBuilder
import ssm.chaincode.dsl.config.ChaincodeSsmConfig
import ssm.tx.config.spring.autoconfigure.SsmTxProperties
import ssm.tx.init.spring.autoconfigure.SsmTxInitAutoConfiguration


class ApplicationContextRunnerTest {

	@Test
	fun `spring context runner must must start`() {
		ApplicationContextRunnerBuilder()
			.buildContext(SsmChaincodeConfigTest.localDockerComposeParams).run { context ->
				assertThat(context).hasSingleBean(FunctionCatalog::class.java)
				assertThat(context).hasSingleBean(SsmTxProperties::class.java)
				assertThat(context).hasBean(SsmTxInitAutoConfiguration::ssmTxInitFunction.name)
			}
	}


	@Test
	fun `spring context must must start`() {
		val context = ApplicationContextBuilder().create(
			types = arrayOf(ApplicationContextBuilder.SimpleConfiguration::class.java),
			config = SsmChaincodeConfigTest.localDockerComposeParams
		)
		assertThat(context.getBean(SsmTxInitAutoConfiguration::ssmTxInitFunction.name)).isNotNull
		assertThat(context.getBean(FunctionCatalog::class.java)).isNotNull
	}


	object SsmChaincodeConfigTest {
		val localDockerCompose = ChaincodeSsmConfig(
			url = "http://localhost:9090"
		)

		val localDockerComposeParams = mapOf(
			"ssm.chaincode.url" to localDockerCompose.url,
			"ssm.signer.admin.name" to "ssm-admin",
			"ssm.signer.admin.key" to "local/admin/ssm-admin"
		)
	}
}
