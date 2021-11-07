package ssm.tx.session.spring.autoconfigure

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding
import ssm.chaincode.dsl.config.SsmChaincodeConfig
import ssm.sdk.sign.config.SignerUserFileConfig

@ConfigurationProperties(prefix = "ssm")
@ConstructorBinding
data class SsmTxSessionPerformActionProperties(
	val chaincode: SsmChaincodeConfig,
	val signer: SignerUserFileConfig
)
