package ssm.couchdb.spring.autoconfig

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding
import ssm.couchdb.dsl.config.SsmCouchdbConfig

@ConfigurationProperties(prefix = "ssm")
@ConstructorBinding
data class SsmCouchdbProperties(
	val couchdb: SsmCouchdbConfig
)
