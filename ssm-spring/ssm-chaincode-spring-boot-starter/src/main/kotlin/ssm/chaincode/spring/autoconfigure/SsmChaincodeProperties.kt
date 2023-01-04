package ssm.chaincode.spring.autoconfigure

import org.springframework.boot.context.properties.ConfigurationProperties
import ssm.chaincode.dsl.config.ChaincodeSsmConfig

@ConfigurationProperties(prefix = "ssm")
data class SsmChaincodeProperties(
	val chaincode: ChaincodeSsmConfig
)
