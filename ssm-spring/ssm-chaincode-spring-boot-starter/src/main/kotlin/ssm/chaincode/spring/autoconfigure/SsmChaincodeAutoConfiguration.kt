package ssm.chaincode.spring.autoconfigure

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import ssm.chaincode.dsl.SsmChaincodeQueries
import ssm.chaincode.dsl.config.SsmChaincodeConfig
import ssm.chaincode.dsl.query.SsmGetAdminFunction
import ssm.chaincode.dsl.query.SsmGetQueryFunction
import ssm.chaincode.dsl.query.SsmGetSessionLogsQueryFunction
import ssm.chaincode.dsl.query.SsmGetSessionQueryFunction
import ssm.chaincode.dsl.query.SsmGetTransactionQueryFunction
import ssm.chaincode.dsl.query.SsmGetUserFunction
import ssm.chaincode.dsl.query.SsmListAdminQueryFunction
import ssm.chaincode.dsl.query.SsmListSessionQueryFunction
import ssm.chaincode.dsl.query.SsmListSsmQueryFunction
import ssm.chaincode.dsl.query.SsmListUserQueryFunction
import ssm.chaincode.f2.query.SsmChaincodeQueryFunctions

@ConditionalOnProperty(prefix = "ssm.chaincode", name = ["url"])
@EnableConfigurationProperties(SsmChaincodeProperties::class)
@Configuration(proxyBeanMethods = false)
class SsmChaincodeAutoConfiguration : SsmChaincodeQueries {

	private val ssmApiQueryFunction: SsmChaincodeQueryFunctions = SsmChaincodeQueryFunctions()

	@Bean
	fun ssmChaincodeConfig(
		ssmChaincodeProperties: SsmChaincodeProperties
	): SsmChaincodeConfig = ssmChaincodeProperties.chaincode

	@Bean
	override fun ssmGetAdminFunction(config: SsmChaincodeConfig): SsmGetAdminFunction {
		return ssmApiQueryFunction.ssmGetAdminFunction(config)
	}

	@Bean
	override fun ssmGetQueryFunction(config: SsmChaincodeConfig): SsmGetQueryFunction {
		return ssmApiQueryFunction.ssmGetQueryFunction(config)
	}

	@Bean
	override fun ssmGetSessionLogsQueryFunction(config: SsmChaincodeConfig): SsmGetSessionLogsQueryFunction {
		return ssmApiQueryFunction.ssmGetSessionLogsQueryFunction(config)
	}

	@Bean
	override fun ssmGetSessionQueryFunction(config: SsmChaincodeConfig): SsmGetSessionQueryFunction {
		return ssmApiQueryFunction.ssmGetSessionQueryFunction(config)
	}

	@Bean
	override fun ssmGetTransactionQueryFunction(config: SsmChaincodeConfig): SsmGetTransactionQueryFunction {
		return ssmApiQueryFunction.ssmGetTransactionQueryFunction(config)
	}

	@Bean
	override fun ssmGetUserFunction(config: SsmChaincodeConfig): SsmGetUserFunction {
		return ssmApiQueryFunction.ssmGetUserFunction(config)
	}

	@Bean
	override fun ssmListAdminQueryFunction(config: SsmChaincodeConfig): SsmListAdminQueryFunction {
		return ssmApiQueryFunction.ssmListAdminQueryFunction(config)
	}

	@Bean
	override fun ssmListSessionQueryFunction(config: SsmChaincodeConfig): SsmListSessionQueryFunction {
		return ssmApiQueryFunction.ssmListSessionQueryFunction(config)
	}

	@Bean
	override fun ssmListSsmQueryFunction(config: SsmChaincodeConfig): SsmListSsmQueryFunction {
		return ssmApiQueryFunction.ssmListSsmQueryFunction(config)
	}

	@Bean
	override fun ssmListUserQueryFunction(config: SsmChaincodeConfig): SsmListUserQueryFunction {
		return ssmApiQueryFunction.ssmListUserQueryFunction(config)
	}
}
