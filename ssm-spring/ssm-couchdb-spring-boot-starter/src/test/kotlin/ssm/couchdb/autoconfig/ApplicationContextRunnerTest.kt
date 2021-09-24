package ssm.couchdb.autoconfig

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.cloud.function.context.FunctionCatalog
import ssm.couchdb.autoconfig.context.ApplicationContextRunnerBuilder
import ssm.couchdb.dsl.config.CouchdbConfig
import ssm.couchdb.spring.autoconfig.SsmCouchdbAutoConfiguration
import ssm.couchdb.spring.autoconfig.SsmCouchdbProperties


class ApplicationContextRunnerTest {

	@Test
	fun someTest() {
		val config = CouchdbConfig(
			url = "http://localhost:5000",
			username = "couchdb",
			password = "couchdb",
			serviceName = "ssm-couchdb-test"
		)
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
}
