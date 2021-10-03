package ssm.data.spring.autoconfigure

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import ssm.api.SsmApiQueryFunctionImpl
import ssm.data.dsl.SsmApiQueryFunctions
import ssm.data.dsl.config.DataSsmConfig
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

	private val ssmApiQueryFunction: SsmApiQueryFunctions = SsmApiQueryFunctionImpl()

	@Bean
	fun dataSsmConfig(dataSsmProperties: DataSsmProperties): DataSsmConfig = dataSsmProperties.data

	@Bean
	override fun dataSsmListQueryFunction(config: DataSsmConfig): DataSsmListQueryFunction {
		return ssmApiQueryFunction.dataSsmListQueryFunction(config)
	}

	@Bean
	override fun dataSsmGetQueryFunction(config: DataSsmConfig): DataSsmGetQueryFunction {
		return ssmApiQueryFunction.dataSsmGetQueryFunction(config)
	}

	@Bean
	override fun dataSsmSessionListQueryFunction(config: DataSsmConfig): DataSsmSessionListQueryFunction {
		return ssmApiQueryFunction.dataSsmSessionListQueryFunction(config)
	}

	@Bean
	override fun dataSsmSessionGetQueryFunction(config: DataSsmConfig): DataSsmSessionGetQueryFunction {
		return ssmApiQueryFunction.dataSsmSessionGetQueryFunction(config)
	}

	@Bean
	override fun dataSsmSessionLogListQueryFunction(config: DataSsmConfig): DataSsmSessionLogListQueryFunction {
		return ssmApiQueryFunction.dataSsmSessionLogListQueryFunction(config)
	}

	@Bean
	override fun dataSsmSessionLogGetQueryFunction(config: DataSsmConfig): DataSsmSessionLogGetQueryFunction {
		return ssmApiQueryFunction.dataSsmSessionLogGetQueryFunction(config)
	}
}
