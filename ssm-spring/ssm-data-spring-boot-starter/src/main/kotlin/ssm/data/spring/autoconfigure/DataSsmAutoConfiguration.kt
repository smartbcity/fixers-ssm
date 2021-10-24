package ssm.data.spring.autoconfigure

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import ssm.api.DataSsmQueryFunctionImpl
import ssm.data.dsl.SsmApiQueryFunctions
import ssm.data.dsl.config.SsmDataConfig
import ssm.data.dsl.features.query.DataSsmGetQueryFunction
import ssm.data.dsl.features.query.DataSsmListQueryFunction
import ssm.data.dsl.features.query.DataSsmSessionGetQueryFunction
import ssm.data.dsl.features.query.DataSsmSessionListQueryFunction
import ssm.data.dsl.features.query.DataSsmSessionLogGetQueryFunction
import ssm.data.dsl.features.query.DataSsmSessionLogListQueryFunction

@ConditionalOnProperty(prefix = "ssm.data.couchdb", name = ["url"])
@EnableConfigurationProperties(DataSsmProperties::class)
@Configuration(proxyBeanMethods = false)
class DataSsmAutoConfiguration : SsmApiQueryFunctions {

	private val ssmApiQueryFunction: SsmApiQueryFunctions = DataSsmQueryFunctionImpl()

	@Bean
	fun dataSsmConfig(dataSsmProperties: DataSsmProperties): SsmDataConfig = dataSsmProperties.data

	@Bean
	override fun dataSsmListQueryFunction(config: SsmDataConfig): DataSsmListQueryFunction {
		return ssmApiQueryFunction.dataSsmListQueryFunction(config)
	}

	@Bean
	override fun dataSsmGetQueryFunction(config: SsmDataConfig): DataSsmGetQueryFunction {
		return ssmApiQueryFunction.dataSsmGetQueryFunction(config)
	}

	@Bean
	override fun dataSsmSessionListQueryFunction(config: SsmDataConfig): DataSsmSessionListQueryFunction {
		return ssmApiQueryFunction.dataSsmSessionListQueryFunction(config)
	}

	@Bean
	override fun dataSsmSessionGetQueryFunction(config: SsmDataConfig): DataSsmSessionGetQueryFunction {
		return ssmApiQueryFunction.dataSsmSessionGetQueryFunction(config)
	}

	@Bean
	override fun dataSsmSessionLogListQueryFunction(config: SsmDataConfig): DataSsmSessionLogListQueryFunction {
		return ssmApiQueryFunction.dataSsmSessionLogListQueryFunction(config)
	}

	@Bean
	override fun dataSsmSessionLogGetQueryFunction(config: SsmDataConfig): DataSsmSessionLogGetQueryFunction {
		return ssmApiQueryFunction.dataSsmSessionLogGetQueryFunction(config)
	}
}
