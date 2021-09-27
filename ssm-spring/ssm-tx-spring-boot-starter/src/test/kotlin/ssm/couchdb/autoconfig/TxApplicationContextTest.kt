package ssm.couchdb.autoconfig

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.cloud.function.context.FunctionCatalog
import ssm.chaincode.dsl.SsmChaincodeConfig
import ssm.couchdb.autoconfig.context.ApplicationContextBuilder
import ssm.couchdb.dsl.config.SsmCouchdbConfig
import ssm.couchdb.spring.tx.SsmTxAutoConfiguration
import ssm.tx.dsl.config.SsmTxConfig


class TxApplicationContextTest {

	@Test
	fun tt() {
		val context = ApplicationContextBuilder().create(
			types = arrayOf(ApplicationContextBuilder.SimpleConfiguration::class.java),
			config = SsmTxConfigTest.localDockerCompose
		)
		Assertions.assertThat(context.getBean(SsmTxAutoConfiguration::ssmTxConfig.name)).isNotNull
		val catalog = context.getBean(FunctionCatalog::class.java)
		Assertions.assertThat(catalog).isNotNull
	}


}
