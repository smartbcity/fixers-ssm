package ssm.chaincode.spring.autoconfigure

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.cloud.function.context.FunctionCatalog
import ssm.chaincode.spring.autoconfigure.context.ApplicationContextBuilder

class SsmChaincodeApplicationContextTest {

	@Test
	fun tt() {
		val context = ApplicationContextBuilder().create(
			types = arrayOf(ApplicationContextBuilder.SimpleConfiguration::class.java),
			config = SsmChaincodeConfigTest.localDockerCompose
		)
		Assertions.assertThat(context.getBean(SsmChaincodeAutoConfiguration::ssmChaincodeConfig.name)).isNotNull
		val catalog = context.getBean(FunctionCatalog::class.java)
		Assertions.assertThat(catalog).isNotNull
	}
}
