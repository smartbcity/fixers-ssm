package ssm.couchdb.spring.autoconfigure

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.cloud.function.context.FunctionCatalog
import ssm.test.cucumber.spring.ApplicationContextBuilder
import ssm.test.cucumber.spring.ApplicationContextRunnerBuilder


class SsmCouchdbApplicationContextRunnerTest {

	@Test
	fun `spring context runner must must start`() {

		ApplicationContextRunnerBuilder()
			.buildContext(
				SsmChaincodeConfigTest.localDockerComposeParams
			).run { context ->
				assertThat(context).hasSingleBean(FunctionCatalog::class.java)
				assertThat(context).hasSingleBean(SsmCouchdbProperties::class.java)
				assertThat(context).hasBean(SsmCouchdbAutoConfiguration::couchdbDatabaseListQueryFunction.name)
				assertThat(context).hasBean(SsmCouchdbAutoConfiguration::couchDbDatabaseGetQueryFunction.name)
				assertThat(context).hasBean(SsmCouchdbAutoConfiguration::couchDbSsmListQueryFunction.name)
				assertThat(context).hasBean(SsmCouchdbAutoConfiguration::couchDbSsmSessionStateListQueryFunction.name)
			}
	}

	@Test
	fun `spring context must must start`() {
		val context = ApplicationContextBuilder().create(
			types = arrayOf(ApplicationContextBuilder.SimpleConfiguration::class.java),
			config = SsmChaincodeConfigTest.localDockerComposeParams
		)
		assertThat(context.getBean(SsmCouchdbAutoConfiguration::couchdbDatabaseListQueryFunction.name)).isNotNull
		val catalog = context.getBean(FunctionCatalog::class.java)
		assertThat(catalog).isNotNull
	}

}
