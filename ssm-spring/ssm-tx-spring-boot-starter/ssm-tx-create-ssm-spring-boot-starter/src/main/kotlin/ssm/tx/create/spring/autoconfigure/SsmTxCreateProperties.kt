package ssm.tx.create.spring.autoconfigure

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding
import ssm.chaincode.dsl.config.ChaincodeSsmConfig
import ssm.sdk.sign.config.SignerAdminFileConfig

@ConfigurationProperties(prefix = "ssm")
@ConstructorBinding
data class SsmTxCreateProperties(
	val chaincode: ChaincodeSsmConfig,
	val signer: SignerAdminFileConfig,
)
