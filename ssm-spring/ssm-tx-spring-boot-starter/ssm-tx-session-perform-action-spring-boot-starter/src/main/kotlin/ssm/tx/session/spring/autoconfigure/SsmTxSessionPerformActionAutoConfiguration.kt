package ssm.tx.session.spring.autoconfigure

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import ssm.chaincode.dsl.config.ChaincodeSsmConfig
import ssm.chaincode.f2.features.command.SsmTxSessionPerformActionFunctionImpl
import ssm.sdk.sign.SignerUserProvider
import ssm.sdk.sign.SignerUserProviderData
import ssm.sdk.sign.config.signer
import ssm.tx.dsl.features.ssm.SsmTxSessionPerformActionFunction

@ConditionalOnProperty(prefix = "ssm.chaincode", name = ["url"])
@EnableConfigurationProperties(SsmTxSessionPerformActionProperties::class)
@Configuration(proxyBeanMethods = false)
class SsmTxSessionPerformActionAutoConfiguration {

	@Bean
	@ConditionalOnMissingBean(ChaincodeSsmConfig::class)
	fun ssmChaincodeConfig(properties: SsmTxSessionPerformActionProperties): ChaincodeSsmConfig = properties.chaincode


	@Bean
	@ConditionalOnMissingBean(SignerUserProvider::class)
	fun signerUserProvider(properties: SsmTxSessionPerformActionProperties): SignerUserProvider =
		SignerUserProviderData(properties.signer.signer())

	@Bean
	fun ssmTxSessionPerformActionFunction(
		config: ChaincodeSsmConfig,
		signerUserProvider: SignerUserProvider
	): SsmTxSessionPerformActionFunction {
		return SsmTxSessionPerformActionFunctionImpl().ssmTxSessionPerformActionFunction(config, signerUserProvider)
	}
}
