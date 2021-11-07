package ssm.data.spring.autoconfigure

import org.springframework.boot.autoconfigure.condition.ConditionalOnBean
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
class DataSsmAutoConfiguration {

	@Bean
	fun dataSsmConfig(dataSsmProperties: DataSsmProperties): SsmDataConfig = dataSsmProperties.data

	@Bean
	fun ssmApiQueryFunctions(ssmDataConfig: SsmDataConfig): SsmApiQueryFunctions = DataSsmQueryFunctionImpl(ssmDataConfig)

}

@ConditionalOnBean(SsmApiQueryFunctions::class)
@Configuration(proxyBeanMethods = false)
class DataSsmF2AutoConfiguration(
	private val ssmApiQueryFunction: SsmApiQueryFunctions
) : SsmApiQueryFunctions {

	@Bean
	override fun dataSsmListQueryFunction(): DataSsmListQueryFunction {
		return ssmApiQueryFunction.dataSsmListQueryFunction()
	}

	@Bean
	override fun dataSsmGetQueryFunction(): DataSsmGetQueryFunction {
		return ssmApiQueryFunction.dataSsmGetQueryFunction()
	}

	@Bean
	override fun dataSsmSessionListQueryFunction(): DataSsmSessionListQueryFunction {
		return ssmApiQueryFunction.dataSsmSessionListQueryFunction()
	}

	@Bean
	override fun dataSsmSessionGetQueryFunction(): DataSsmSessionGetQueryFunction {
		return ssmApiQueryFunction.dataSsmSessionGetQueryFunction()
	}

	@Bean
	override fun dataSsmSessionLogListQueryFunction(): DataSsmSessionLogListQueryFunction {
		return ssmApiQueryFunction.dataSsmSessionLogListQueryFunction()
	}

	@Bean
	override fun dataSsmSessionLogGetQueryFunction(): DataSsmSessionLogGetQueryFunction {
		return ssmApiQueryFunction.dataSsmSessionLogGetQueryFunction()
	}

}
