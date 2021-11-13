package ssm.tx.session.start.spring.autoconfigure

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import ssm.chaincode.dsl.config.ChaincodeSsmConfig
import ssm.chaincode.f2.features.command.SsmTxSessionStartFunctionImpl
import ssm.sdk.sign.SignerAdminProvider
import ssm.sdk.sign.SignerAdminProviderData
import ssm.sdk.sign.config.signer
import ssm.tx.dsl.features.ssm.SsmTxSessionStartFunction

@ConditionalOnProperty(prefix = "ssm.chaincode", name = ["url"])
@EnableConfigurationProperties(SsmTxSessionStartProperties::class)
@Configuration(proxyBeanMethods = false)
class SsmSessionStartAutoConfiguration {

	@Bean
	@ConditionalOnMissingBean(ChaincodeSsmConfig::class)
	fun ssmChaincodeConfig(ssmCreateProperties: SsmTxSessionStartProperties): ChaincodeSsmConfig =
		ssmCreateProperties.chaincode

	@Bean
	@ConditionalOnMissingBean(SignerAdminProvider::class)
	fun signerAdminProvider(ssmCreateProperties: SsmTxSessionStartProperties): SignerAdminProvider =
		SignerAdminProviderData(ssmCreateProperties.signer.signer())

	@Bean
	fun ssmTxSessionStartFunction(config: ChaincodeSsmConfig, signerAdminProvider: SignerAdminProvider): SsmTxSessionStartFunction =
		SsmTxSessionStartFunctionImpl().ssmTxSessionStartFunction(config, signerAdminProvider)

}
