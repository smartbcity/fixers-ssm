package ssm.tx.create.spring.autoconfigure

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import ssm.chaincode.dsl.config.SsmChaincodeConfig
import ssm.chaincode.f2.SsmCreateFunction
import ssm.chaincode.f2.SsmCreateFunctionImpl

@ConditionalOnProperty(prefix = "ssm.chaincode", name = ["url"])
@EnableConfigurationProperties(SsmCreateProperties::class)
@Configuration(proxyBeanMethods = false)
class SsmCreateAutoConfiguration {


	@Bean
	@ConditionalOnMissingBean(SsmChaincodeConfig::class)
	fun ssmTxConfig(ssmCreateProperties: SsmCreateProperties): SsmChaincodeConfig = ssmCreateProperties.chaincode

	@Bean
	fun ssmCreateFunction(config: SsmChaincodeConfig): SsmCreateFunction {
		return SsmCreateFunctionImpl().ssmCreateFunction(config)
	}

}
