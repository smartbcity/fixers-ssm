package ssm.tx.create.spring.autoconfigure

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import ssm.chaincode.dsl.config.SsmChaincodeConfig
import ssm.chaincode.f2.features.command.SsmTxCreateFunctionImpl
import ssm.sdk.sign.SignerAdminProvider
import ssm.sdk.sign.SignerAdminProviderData
import ssm.sdk.sign.config.adminSigner
import ssm.tx.dsl.features.ssm.SsmTxCreateFunction

@ConditionalOnProperty(prefix = "ssm.chaincode", name = ["url"])
@EnableConfigurationProperties(SsmTxCreateProperties::class)
@Configuration(proxyBeanMethods = false)
class SsmTxCreateAutoConfiguration {

	@Bean
	@ConditionalOnMissingBean(SsmChaincodeConfig::class)
	fun ssmTxConfig(ssmTxCreateProperties: SsmTxCreateProperties): SsmChaincodeConfig =
		ssmTxCreateProperties.chaincode

	@Bean
	@ConditionalOnMissingBean(SignerAdminProvider::class)
	fun signerAdminProvider(ssmTxCreateProperties: SsmTxCreateProperties): SignerAdminProvider =
		SignerAdminProviderData(ssmTxCreateProperties.signer.adminSigner())

	@Bean
	fun ssmTxCreateFunction(config: SsmChaincodeConfig, signerAdminProvider: SignerAdminProvider): SsmTxCreateFunction =
		SsmTxCreateFunctionImpl().ssmTxCreateFunction(config, signerAdminProvider)

}
