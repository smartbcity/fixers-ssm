package ssm.tx.spring.autoconfigure

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import ssm.api.SsmApiQueryFunctionImpl
import ssm.couchdb.dsl.config.SsmCouchdbConfig
import ssm.couchdb.dsl.query.CouchdbSsmSessionStateListQueryFunction
import ssm.tx.dsl.SsmApiQueryFunctions
import ssm.tx.dsl.config.SsmTxConfig
import ssm.tx.dsl.features.query.TxSsmGetQueryFunction
import ssm.tx.dsl.features.query.TxSsmListQueryFunction
import ssm.tx.dsl.features.query.TxSsmSessionGetQueryFunction
import ssm.tx.dsl.features.query.TxSsmSessionListQueryFunction
import ssm.tx.dsl.features.query.TxSsmSessionLogGetQueryFunction
import ssm.tx.dsl.features.query.TxSsmSessionLogListQueryFunction

@ConditionalOnProperty(prefix = "ssm.tx.couchdb", name = ["url"])
@EnableConfigurationProperties(SsmTxProperties::class)
@Configuration(proxyBeanMethods = false)
class SsmTxAutoConfiguration : SsmApiQueryFunctions {

	private val ssmApiQueryFunction: SsmApiQueryFunctions = SsmApiQueryFunctionImpl()

	@Bean
	fun ssmTxConfig(ssmTxProperties: SsmTxProperties): SsmTxConfig = ssmTxProperties.tx

	@Bean
	override fun txSsmListQueryFunction(config: SsmTxConfig): TxSsmListQueryFunction {
		return ssmApiQueryFunction.txSsmListQueryFunction(config)
	}

	@Bean
	override fun txSsmGetQueryFunction(config: SsmTxConfig): TxSsmGetQueryFunction {
		return ssmApiQueryFunction.txSsmGetQueryFunction(config)
	}

	@Bean
	override fun txSsmSessionListQueryFunction(config: SsmTxConfig): TxSsmSessionListQueryFunction {
		return ssmApiQueryFunction.txSsmSessionListQueryFunction(config)
	}

	@Bean
	override fun txSsmSessionGetQueryFunction(config: SsmTxConfig): TxSsmSessionGetQueryFunction {
		return ssmApiQueryFunction.txSsmSessionGetQueryFunction(config)
	}

	@Bean
	override fun txSsmSessionLogListQueryFunction(config: SsmTxConfig): TxSsmSessionLogListQueryFunction {
		return ssmApiQueryFunction.txSsmSessionLogListQueryFunction(config)
	}

	@Bean
	override fun txSsmSessionLogGetQueryFunction(config: SsmTxConfig): TxSsmSessionLogGetQueryFunction {
		return ssmApiQueryFunction.txSsmSessionLogGetQueryFunction(config)
	}
}
