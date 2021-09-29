package ssm.session.start.spring.autoconfigure

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding
import ssm.chaincode.dsl.config.SsmChaincodeConfig

@ConfigurationProperties(prefix = "ssm")
@ConstructorBinding
data class SsmSessionStartProperties(
	val chaincode: SsmChaincodeConfig
)
