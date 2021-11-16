package ssm.chaincode.spring.autoconfigure

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding
import ssm.chaincode.dsl.config.ChaincodeSsmConfig

@ConfigurationProperties(prefix = "ssm")
@ConstructorBinding
data class SsmChaincodeProperties(
	val chaincode: ChaincodeSsmConfig
)
