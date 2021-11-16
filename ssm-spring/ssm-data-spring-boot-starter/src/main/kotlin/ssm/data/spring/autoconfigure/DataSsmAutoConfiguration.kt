package ssm.data.spring.autoconfigure

import org.springframework.boot.autoconfigure.condition.ConditionalOnBean
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import ssm.api.DataSsmQueryFunctionImpl
import ssm.chaincode.dsl.config.ChaincodeSsmConfig
import ssm.couchdb.dsl.config.CouchdbSsmConfig
import ssm.data.dsl.SsmApiQueryFunctions
import ssm.data.dsl.config.DataSsmConfig
import ssm.data.dsl.features.query.DataSsmGetQueryFunction
import ssm.data.dsl.features.query.DataSsmListQueryFunction
import ssm.data.dsl.features.query.DataSsmSessionGetQueryFunction
import ssm.data.dsl.features.query.DataSsmSessionListQueryFunction
import ssm.data.dsl.features.query.DataSsmSessionLogGetQueryFunction
import ssm.data.dsl.features.query.DataSsmSessionLogListQueryFunction


@EnableConfigurationProperties(DataSsmProperties::class)
@Configuration(proxyBeanMethods = false)
class DataSsmAutoConfiguration {

	@Bean
	@ConditionalOnMissingBean(CouchdbSsmConfig::class)
	@ConditionalOnProperty(prefix = "ssm.couchdb", name = ["url"])
	fun couchdbSsmConfig(dataSsmProperties: DataSsmProperties): CouchdbSsmConfig? {
		return dataSsmProperties.couchdb
	}
	@Bean
	@ConditionalOnMissingBean(ChaincodeSsmConfig::class)
	@ConditionalOnProperty(prefix = "ssm.chaincode", name = ["url"])
	fun ssmChaincodeConfig(dataSsmProperties: DataSsmProperties): ChaincodeSsmConfig? {
		return dataSsmProperties.chaincode
	}

	@Bean
	@ConditionalOnMissingBean(DataSsmConfig::class)
	@ConditionalOnBean(value = [CouchdbSsmConfig::class, ChaincodeSsmConfig::class])
	fun dataSsmConfig(
		couchdbSsmConfig: CouchdbSsmConfig,
		chaincodeSsmConfig: ChaincodeSsmConfig
	): DataSsmConfig {
		return DataSsmConfig(couchdbSsmConfig, chaincodeSsmConfig)
	}

	@Bean
	@ConditionalOnMissingBean(SsmApiQueryFunctions::class)
	@ConditionalOnBean(value = [DataSsmConfig::class])
	fun ssmApiQueryFunctions(ssmDataConfig: DataSsmConfig): SsmApiQueryFunctions {
		return DataSsmQueryFunctionImpl(ssmDataConfig)
	}
}

@ConditionalOnBean(SsmApiQueryFunctions::class)
@Configuration(proxyBeanMethods = false)
class DataSsmF2AutoConfiguration(
	private val ssmApiQueryFunctions: SsmApiQueryFunctions
) : SsmApiQueryFunctions {

	@Bean
	override fun dataSsmListQueryFunction(): DataSsmListQueryFunction {
		return ssmApiQueryFunctions.dataSsmListQueryFunction()
	}

	@Bean
	override fun dataSsmGetQueryFunction(): DataSsmGetQueryFunction {
		return ssmApiQueryFunctions.dataSsmGetQueryFunction()
	}

	@Bean
	override fun dataSsmSessionListQueryFunction(): DataSsmSessionListQueryFunction {
		return ssmApiQueryFunctions.dataSsmSessionListQueryFunction()
	}

	@Bean
	override fun dataSsmSessionGetQueryFunction(): DataSsmSessionGetQueryFunction {
		return ssmApiQueryFunctions.dataSsmSessionGetQueryFunction()
	}

	@Bean
	override fun dataSsmSessionLogListQueryFunction(): DataSsmSessionLogListQueryFunction {
		return ssmApiQueryFunctions.dataSsmSessionLogListQueryFunction()
	}

	@Bean
	override fun dataSsmSessionLogGetQueryFunction(): DataSsmSessionLogGetQueryFunction {
		return ssmApiQueryFunctions.dataSsmSessionLogGetQueryFunction()
	}
}
