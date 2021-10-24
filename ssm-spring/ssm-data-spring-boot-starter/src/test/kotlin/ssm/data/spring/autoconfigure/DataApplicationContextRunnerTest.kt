package ssm.data.spring.autoconfigure

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.cloud.function.context.FunctionCatalog
import ssm.chaincode.dsl.config.SsmChaincodeConfig
import ssm.couchdb.dsl.config.SsmCouchdbConfig
import ssm.data.dsl.config.SsmDataConfig
import ssm.test.spring.ApplicationContextBuilder
import ssm.test.spring.ApplicationContextRunnerBuilder

class DataApplicationContextRunnerTest {

	@Test
	fun `spring context runner must must start`() {
		ApplicationContextRunnerBuilder()
			.buildContext(TestConfiguration.localDockerComposeParams).run { context ->
				assertThat(context).hasSingleBean(FunctionCatalog::class.java)
				assertThat(context).hasSingleBean(DataSsmProperties::class.java)
				assertThat(context).hasBean(DataSsmAutoConfiguration::dataSsmListQueryFunction.name)
				assertThat(context).hasBean(DataSsmAutoConfiguration::dataSsmGetQueryFunction.name)
				assertThat(context).hasBean(DataSsmAutoConfiguration::dataSsmSessionListQueryFunction.name)
				assertThat(context).hasBean(DataSsmAutoConfiguration::dataSsmSessionGetQueryFunction.name)
				assertThat(context).hasBean(DataSsmAutoConfiguration::dataSsmSessionLogListQueryFunction.name)
				assertThat(context).hasBean(DataSsmAutoConfiguration::dataSsmSessionLogGetQueryFunction.name)
		}
	}

	@Test
	fun `spring context must must start`() {
		val context = ApplicationContextBuilder().create(
			types = arrayOf(ApplicationContextBuilder.SimpleConfiguration::class.java),
			config = TestConfiguration.localDockerComposeParams
		)
		assertThat(context.getBean(DataSsmAutoConfiguration::dataSsmConfig.name)).isNotNull
		assertThat(context.getBean(FunctionCatalog::class.java)).isNotNull
	}


	object TestConfiguration {
		private val localDockerCompose = SsmDataConfig(
			couchdb = SsmCouchdbConfig(
				url = "http://localhost:5000",
				username = "couchdb",
				password = "couchdb",
				serviceName = "ssm-couchdb-test"
			),
			chaincode = SsmChaincodeConfig(
				url = "http://localhost:9090"
			),
		)

		val localDockerComposeParams = mapOf(
			"ssm.data.chaincode.url" to localDockerCompose.chaincode.url,
			"ssm.data.couchdb.url" to localDockerCompose.couchdb.url,
			"ssm.data.couchdb.username" to localDockerCompose.couchdb.username,
			"ssm.data.couchdb.password" to localDockerCompose.couchdb.password,
			"ssm.data.couchdb.serviceName" to localDockerCompose.couchdb.serviceName,
		)
	}
}
