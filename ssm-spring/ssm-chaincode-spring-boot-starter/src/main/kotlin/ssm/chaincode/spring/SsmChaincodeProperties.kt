package ssm.chaincode.spring

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding
import ssm.chaincode.dsl.config.SsmChaincodeConfig

@ConfigurationProperties(prefix = "ssm")
@ConstructorBinding
data class SsmChaincodeProperties(
	val chaincode: SsmChaincodeConfig
)
