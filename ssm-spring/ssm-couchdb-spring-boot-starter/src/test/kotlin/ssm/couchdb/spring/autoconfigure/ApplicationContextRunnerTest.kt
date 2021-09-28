package ssm.couchdb.spring.autoconfigure

import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.cloud.function.context.FunctionCatalog
import ssm.couchdb.spring.autoconfigure.context.ApplicationContextRunnerBuilder
import ssm.couchdb.dsl.config.SsmCouchdbConfig
import ssm.couchdb.spring.autoconfigure.context.ApplicationContextBuilder


class ApplicationContextRunnerTest {
	private val config = SsmCouchdbConfig(
		url = "http://localhost:5000",
		username = "couchdb",
		password = "couchdb",
		serviceName = "ssm-couchdb-test"
	)
	@Test
	fun `spring context must must start`() {

		ApplicationContextRunnerBuilder()
			.buildContext(config).run { context ->
			assertThat(context).hasSingleBean(FunctionCatalog::class.java)
			assertThat(context).hasSingleBean(SsmCouchdbProperties::class.java)
			assertThat(context).hasBean(SsmCouchdbAutoConfiguration::couchdbDatabaseListQueryFunction.name)
			assertThat(context).hasBean(SsmCouchdbAutoConfiguration::couchDbDatabaseGetQueryFunction.name)
			assertThat(context).hasBean(SsmCouchdbAutoConfiguration::couchDbSsmListQueryFunction.name)
			assertThat(context).hasBean(SsmCouchdbAutoConfiguration::couchDbSsmSessionStateListQueryFunction.name)
		}
	}

	@Test
	fun tt() {
		val context = ApplicationContextBuilder().create(
			types = arrayOf(ApplicationContextBuilder.SimpleConfiguration::class.java),
			config = config
		)
		Assertions.assertThat(context.getBean(SsmCouchdbAutoConfiguration::couchdbDatabaseListQueryFunction.name)).isNotNull
		val catalog = context.getBean(FunctionCatalog::class.java)
		Assertions.assertThat(catalog).isNotNull
	}

}
