package ssm.data.spring.autoconfigure

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.cloud.function.context.FunctionCatalog
import ssm.bdd.spring.autoconfigure.ApplicationContextBuilder
import ssm.bdd.spring.autoconfigure.ApplicationContextRunnerBuilder
import ssm.chaincode.dsl.config.ChaincodeSsmConfig
import ssm.couchdb.dsl.config.CouchdbSsmConfig
import ssm.data.dsl.SsmApiQueryFunctions
import ssm.data.dsl.config.DataSsmConfig

class DataApplicationContextRunnerTest {

	@Test
	fun `spring context runner must must start`() {
		ApplicationContextRunnerBuilder()
			.buildContext(TestConfiguration.localDockerComposeParams).run { context ->
				assertThat(context).hasSingleBean(FunctionCatalog::class.java)
				assertThat(context).hasSingleBean(DataSsmProperties::class.java)
				assertThat(context).hasBean(SsmApiQueryFunctions::dataSsmListQueryFunction.name)
				assertThat(context).hasBean(SsmApiQueryFunctions::dataSsmGetQueryFunction.name)
				assertThat(context).hasBean(SsmApiQueryFunctions::dataSsmSessionListQueryFunction.name)
				assertThat(context).hasBean(SsmApiQueryFunctions::dataSsmSessionGetQueryFunction.name)
				assertThat(context).hasBean(SsmApiQueryFunctions::dataSsmSessionLogListQueryFunction.name)
				assertThat(context).hasBean(SsmApiQueryFunctions::dataSsmSessionLogGetQueryFunction.name)
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
		private val localDockerCompose = DataSsmConfig(
			couchdb = CouchdbSsmConfig(
				url = "http://localhost:5984",
				username = "couchdb",
				password = "couchdb",
				serviceName = "ssm-couchdb-test"
			),
			chaincode = ChaincodeSsmConfig(
				url = "http://localhost:9090"
			),
		)

		val localDockerComposeParams = mapOf(
			"ssm.chaincode.url" to localDockerCompose.chaincode.url,
			"ssm.couchdb.url" to localDockerCompose.couchdb.url,
			"ssm.couchdb.username" to localDockerCompose.couchdb.username,
			"ssm.couchdb.password" to localDockerCompose.couchdb.password,
			"ssm.couchdb.serviceName" to localDockerCompose.couchdb.serviceName,
		)
	}
}
