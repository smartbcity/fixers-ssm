package ssm.session.start.spring.autoconfigure

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import ssm.chaincode.dsl.config.SsmChaincodeConfig
import ssm.chaincode.f2.SsmSessionStartFunction
import ssm.chaincode.f2.SsmSessionStartFunctionImpl

@ConditionalOnProperty(prefix = "ssm.chaincode", name = ["url"])
@EnableConfigurationProperties(SsmSessionStartProperties::class)
@Configuration(proxyBeanMethods = false)
class SsmSessionStartAutoConfiguration {


	@Bean
	fun ssmChaincodeConfig(ssmCreateProperties: SsmSessionStartProperties): SsmChaincodeConfig = ssmCreateProperties.chaincode

	@Bean
	fun ssmSessionStartFunction(config: SsmChaincodeConfig): SsmSessionStartFunction {
		return SsmSessionStartFunctionImpl().ssmSessionStartFunction(config)
	}
}
