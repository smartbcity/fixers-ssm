package ssm.tx.session.spring.autoconfigure

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import ssm.chaincode.dsl.config.SsmChaincodeConfig
import ssm.chaincode.f2.SsmSessionPerformActionFunction
import ssm.chaincode.f2.SsmSessionPerformActionFunctionImpl

@ConditionalOnProperty(prefix = "ssm.chaincode", name = ["url"])
@EnableConfigurationProperties(SsmSessionPerformActionProperties::class)
@Configuration(proxyBeanMethods = false)
class SsmSessionPerformActionAutoConfiguration {

	@Bean
	@ConditionalOnMissingBean(SsmChaincodeConfig::class)
	fun ssmChaincodeConfig(ssmCreateProperties: SsmSessionPerformActionProperties): SsmChaincodeConfig = ssmCreateProperties.chaincode

	@Bean
	fun ssmSessionPerformActionFunction(config: SsmChaincodeConfig): SsmSessionPerformActionFunction {
		return SsmSessionPerformActionFunctionImpl().ssmSessionPerformActionFunction(config)
	}
}
