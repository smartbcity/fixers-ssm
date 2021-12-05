package ssm.data.spring.autoconfigure

import f2.dsl.fnc.f2Function
import f2.dsl.fnc.invoke
import org.slf4j.LoggerFactory
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
import ssm.data.dsl.features.query.DataChaincodeListQueryFunction
import ssm.data.dsl.features.query.DataSsmGetQueryFunction
import ssm.data.dsl.features.query.DataSsmListQueryFunction
import ssm.data.dsl.features.query.DataSsmSessionGetQueryFunction
import ssm.data.dsl.features.query.DataSsmSessionListQueryFunction
import ssm.data.dsl.features.query.DataSsmSessionLogGetQueryFunction
import ssm.data.dsl.features.query.DataSsmSessionLogListQueryFunction


@EnableConfigurationProperties(DataSsmProperties::class)
@Configuration(proxyBeanMethods = false)
class DataSsmAutoConfiguration {

	private val logger = LoggerFactory.getLogger(DataSsmAutoConfiguration::class.java)

	@Bean
	@ConditionalOnMissingBean(CouchdbSsmConfig::class)
	@ConditionalOnProperty(prefix = "ssm.couchdb", name = ["url"])
	fun couchdbSsmConfig(dataSsmProperties: DataSsmProperties): CouchdbSsmConfig? {
		logger.debug("Configuration of ${DataSsmAutoConfiguration::couchdbSsmConfig.name}...")
		return dataSsmProperties.couchdb
	}
	@Bean
	@ConditionalOnMissingBean(ChaincodeSsmConfig::class)
	@ConditionalOnProperty(prefix = "ssm.chaincode", name = ["url"])
	fun ssmChaincodeConfig(dataSsmProperties: DataSsmProperties): ChaincodeSsmConfig? {
		logger.debug("Configuration of ${DataSsmAutoConfiguration::ssmChaincodeConfig.name}...")
		return dataSsmProperties.chaincode
	}

	@Bean
	@ConditionalOnMissingBean(DataSsmConfig::class)
	@ConditionalOnBean(value = [CouchdbSsmConfig::class, ChaincodeSsmConfig::class])
	fun dataSsmConfig(
		couchdbSsmConfig: CouchdbSsmConfig,
		chaincodeSsmConfig: ChaincodeSsmConfig
	): DataSsmConfig {
		logger.debug("Configuration of ${DataSsmAutoConfiguration::dataSsmConfig.name}...")
		return DataSsmConfig(couchdbSsmConfig, chaincodeSsmConfig)
	}

	@Bean
	@ConditionalOnMissingBean(SsmApiQueryFunctions::class)
	@ConditionalOnBean(value = [DataSsmConfig::class])
	fun ssmApiQueryFunctions(ssmDataConfig: DataSsmConfig): SsmApiQueryFunctions {
		logger.debug("Configuration of ${DataSsmAutoConfiguration::ssmApiQueryFunctions.name}...")
		return DataSsmQueryFunctionImpl(ssmDataConfig)
	}
}

@ConditionalOnBean(SsmApiQueryFunctions::class)
@Configuration(proxyBeanMethods = false)
class DataSsmF2AutoConfiguration(
	private val ssmApiQueryFunctions: SsmApiQueryFunctions
) : SsmApiQueryFunctions {

	private val logger = LoggerFactory.getLogger(DataSsmAutoConfiguration::class.java)

	@Bean
	override fun dataChaincodeListQueryFunction(): DataChaincodeListQueryFunction {
		logger.debug("Configuration of ${DataSsmF2AutoConfiguration::dataSsmListQueryFunction.name}...")
		return ssmApiQueryFunctions.dataChaincodeListQueryFunction()
	}

	@Bean
	override fun dataSsmListQueryFunction(): DataSsmListQueryFunction {
		logger.debug("Configuration of ${DataSsmF2AutoConfiguration::dataSsmListQueryFunction.name}...")
		return ssmApiQueryFunctions.dataSsmListQueryFunction()
	}

	@Bean
	override fun dataSsmGetQueryFunction(): DataSsmGetQueryFunction {
		logger.debug("Configuration of ${DataSsmF2AutoConfiguration::dataSsmGetQueryFunction.name}...")
		return ssmApiQueryFunctions.dataSsmGetQueryFunction()
	}

	@Bean
	override fun dataSsmSessionListQueryFunction(): DataSsmSessionListQueryFunction {
		logger.debug("Configuration of ${DataSsmF2AutoConfiguration::dataSsmSessionListQueryFunction.name}...")
		return ssmApiQueryFunctions.dataSsmSessionListQueryFunction()
	}

	@Bean
	override fun dataSsmSessionGetQueryFunction(): DataSsmSessionGetQueryFunction {
		logger.debug("Configuration of ${DataSsmF2AutoConfiguration::dataSsmSessionGetQueryFunction.name}...")
		return ssmApiQueryFunctions.dataSsmSessionGetQueryFunction()
	}

	@Bean
	override fun dataSsmSessionLogListQueryFunction(): DataSsmSessionLogListQueryFunction {
		logger.debug("Configuration of ${DataSsmF2AutoConfiguration::dataSsmSessionLogListQueryFunction.name}...")
		return ssmApiQueryFunctions.dataSsmSessionLogListQueryFunction()
	}

	@Bean
	override fun dataSsmSessionLogGetQueryFunction(): DataSsmSessionLogGetQueryFunction {
		logger.debug("Configuration of ${DataSsmF2AutoConfiguration::dataSsmSessionLogGetQueryFunction.name}...")
		return ssmApiQueryFunctions.dataSsmSessionLogGetQueryFunction()
	}
}
