package ssm.chaincode.spring.autoconfigure

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.cloud.function.context.FunctionCatalog
import ssm.chaincode.spring.autoconfigure.context.ApplicationContextRunnerBuilder


class SsmChaincodeApplicationContextRunnerTest {

	@Test
	fun someTest() {
		ApplicationContextRunnerBuilder()
			.buildContext(SsmChaincodeConfigTest.localDockerCompose).run { context ->
				assertThat(context).hasSingleBean(FunctionCatalog::class.java)
				assertThat(context).hasSingleBean(SsmChaincodeProperties::class.java)
				assertThat(context).hasBean(SsmChaincodeAutoConfiguration::ssmGetAdminFunction.name)
				assertThat(context).hasBean(SsmChaincodeAutoConfiguration::ssmGetSessionLogsQueryFunction.name)
				assertThat(context).hasBean(SsmChaincodeAutoConfiguration::ssmGetSessionLogsQueryFunction.name)
				assertThat(context).hasBean(SsmChaincodeAutoConfiguration::ssmGetSessionQueryFunction.name)
				assertThat(context).hasBean(SsmChaincodeAutoConfiguration::ssmGetTransactionQueryFunction.name)
				assertThat(context).hasBean(SsmChaincodeAutoConfiguration::ssmGetUserFunction.name)
				assertThat(context).hasBean(SsmChaincodeAutoConfiguration::ssmListAdminQueryFunction.name)
				assertThat(context).hasBean(SsmChaincodeAutoConfiguration::ssmListSessionQueryFunction.name)
				assertThat(context).hasBean(SsmChaincodeAutoConfiguration::ssmListSsmQueryFunction.name)
				assertThat(context).hasBean(SsmChaincodeAutoConfiguration::ssmListUserQueryFunction.name)
			}
	}
}
