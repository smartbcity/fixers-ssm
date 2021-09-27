package ssm.couchdb.autoconfig

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.cloud.function.context.FunctionCatalog
import ssm.chaincode.autoconfig.context.ApplicationContextBuilder
import ssm.couchdb.dsl.config.SsmCouchdbConfig
import ssm.couchdb.spring.autoconfig.SsmCouchdbAutoConfiguration


class ApplicationContextTest {

	@Test
	fun tt() {
		val context = ApplicationContextBuilder().create(
			types = arrayOf(ApplicationContextBuilder.SimpleConfiguration::class.java),
			config = SsmCouchdbConfig(
				url = "http://localhost:5000",
				username = "couchdb",
				password = "couchdb",
				serviceName = "ssm-couchdb-test"
			)
		)
		Assertions.assertThat(context.getBean(SsmCouchdbAutoConfiguration::couchdbDatabaseListQueryFunction.name)).isNotNull
		val catalog = context.getBean(FunctionCatalog::class.java)
		Assertions.assertThat(catalog).isNotNull
	}


}
