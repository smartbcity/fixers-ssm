package ssm.tx.create.spring.autoconfigure

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding
import ssm.chaincode.dsl.config.SsmChaincodeConfig

@ConfigurationProperties(prefix = "ssm")
@ConstructorBinding
data class SsmCreateProperties(
	val chaincode: SsmChaincodeConfig
)
