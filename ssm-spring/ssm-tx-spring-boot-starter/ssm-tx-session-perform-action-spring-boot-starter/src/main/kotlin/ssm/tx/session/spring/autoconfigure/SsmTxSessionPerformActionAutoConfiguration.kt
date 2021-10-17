package ssm.tx.session.spring.autoconfigure

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import ssm.chaincode.dsl.config.SsmChaincodeConfig
import ssm.chaincode.f2.SsmTxSessionPerformActionFunctionImpl
import ssm.sdk.sign.SignerProvider
import ssm.sdk.sign.SignerProviderData
import ssm.sdk.sign.config.signer
import ssm.tx.dsl.features.ssm.SsmTxSessionPerformActionFunction

@ConditionalOnProperty(prefix = "ssm.chaincode", name = ["url"])
@EnableConfigurationProperties(SsmTxSessionPerformActionProperties::class)
@Configuration(proxyBeanMethods = false)
class SsmTxSessionPerformActionAutoConfiguration {

	@Bean
	@ConditionalOnMissingBean(SsmChaincodeConfig::class)
	fun ssmChaincodeConfig(properties: SsmTxSessionPerformActionProperties): SsmChaincodeConfig = properties.chaincode


	@Bean
	@ConditionalOnMissingBean(SignerProvider::class)
	fun signer(properties: SsmTxSessionPerformActionProperties): SignerProvider =
		SignerProviderData(properties.signer.signer())

	@Bean
	fun ssmTxSessionPerformActionFunction(config: SsmChaincodeConfig, signerProvider: SignerProvider): SsmTxSessionPerformActionFunction {
		return SsmTxSessionPerformActionFunctionImpl().ssmTxSessionPerformActionFunction(config, signerProvider)
	}
}
