package ssm.tx.session.start.spring.autoconfigure

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding
import ssm.chaincode.dsl.config.SsmChaincodeConfig
import ssm.sdk.sign.config.SignerAdminFileConfig

@ConfigurationProperties(prefix = "ssm")
@ConstructorBinding
data class SsmTxSessionStartProperties(
	val chaincode: SsmChaincodeConfig,
	val signer: SignerAdminFileConfig
)
