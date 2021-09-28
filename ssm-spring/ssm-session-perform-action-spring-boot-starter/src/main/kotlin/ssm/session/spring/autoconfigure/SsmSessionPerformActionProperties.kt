package ssm.session.spring.autoconfigure

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding
import ssm.chaincode.dsl.config.SsmChaincodeConfig

@ConfigurationProperties(prefix = "ssm")
@ConstructorBinding
data class SsmSessionPerformActionProperties(
	val chaincode: SsmChaincodeConfig
)
