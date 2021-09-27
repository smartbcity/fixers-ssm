package ssm.couchdb.autoconfig

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.cloud.function.context.FunctionCatalog
import ssm.chaincode.autoconfig.context.ApplicationContextRunnerBuilder
import ssm.couchdb.spring.tx.SsmTxAutoConfiguration
import ssm.couchdb.spring.tx.SsmTxProperties


class TxApplicationContextRunnerTest {

	@Test
	fun someTest() {

		ApplicationContextRunnerBuilder()
			.buildContext(SsmTxConfigTest.localDockerCompose).run { context ->
			assertThat(context).hasSingleBean(FunctionCatalog::class.java)
			assertThat(context).hasSingleBean(SsmTxProperties::class.java)
			assertThat(context).hasBean(SsmTxAutoConfiguration::txSsmListQueryFunction.name)
				assertThat(context).hasBean(SsmTxAutoConfiguration::txSsmGetQueryFunction.name)
				assertThat(context).hasBean(SsmTxAutoConfiguration::txSsmSessionListQueryFunction.name)
				assertThat(context).hasBean(SsmTxAutoConfiguration::txSsmSessionGetQueryFunction.name)
				assertThat(context).hasBean(SsmTxAutoConfiguration::txSsmSessionLogListQueryFunction.name)
				assertThat(context).hasBean(SsmTxAutoConfiguration::txSsmSessionLogGetQueryFunction.name)
		}
	}
}
