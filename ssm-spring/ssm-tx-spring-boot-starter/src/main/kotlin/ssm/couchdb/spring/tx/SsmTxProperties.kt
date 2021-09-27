package ssm.couchdb.spring.tx

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding
import ssm.tx.dsl.config.SsmTxConfig

@ConfigurationProperties(prefix = "ssm")
@ConstructorBinding
data class SsmTxProperties(
	val tx: SsmTxConfig
)
