package ssm.tx.create.spring.autoconfigure

import org.springframework.boot.autoconfigure.condition.ConditionalOnBean
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import ssm.chaincode.dsl.config.ChaincodeSsmConfig
import ssm.chaincode.f2.features.command.SsmTxCreateFunctionImpl
import ssm.sdk.sign.SignerAdminProvider
import ssm.sdk.sign.SignerAdminProviderData
import ssm.sdk.sign.config.signer
import ssm.tx.dsl.features.ssm.SsmTxCreateFunction

@ConditionalOnProperty(prefix = "ssm.chaincode", name = ["url"])
@EnableConfigurationProperties(SsmTxCreateProperties::class)
@Configuration(proxyBeanMethods = false)
class SsmTxCreateAutoConfiguration {

	@Bean
	@ConditionalOnMissingBean(ChaincodeSsmConfig::class)
	fun chaincodeSsmConfig(ssmTxCreateProperties: SsmTxCreateProperties): ChaincodeSsmConfig =
		ssmTxCreateProperties.chaincode

	@Bean
	@ConditionalOnMissingBean(SignerAdminProvider::class)
	fun signerAdminProvider(ssmTxCreateProperties: SsmTxCreateProperties): SignerAdminProvider =
		SignerAdminProviderData(ssmTxCreateProperties.signer.signer())

}

@ConditionalOnBean(value = [SignerAdminProvider::class, SignerAdminProvider::class])
@Configuration(proxyBeanMethods = false)
class SsmTxF2CreateAutoConfiguration {

	@Bean
	fun ssmTxCreateFunction(chaincodeSsmConfig: ChaincodeSsmConfig, signerAdminProvider: SignerAdminProvider): SsmTxCreateFunction =
		SsmTxCreateFunctionImpl().ssmTxCreateFunction(chaincodeSsmConfig, signerAdminProvider)

}
