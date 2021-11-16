package ssm.couchdb.spring.autoconfigure

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding
import ssm.couchdb.dsl.config.CouchdbSsmConfig

@ConfigurationProperties(prefix = "ssm")
@ConstructorBinding
data class CouchdbSsmProperties(
	val couchdb: CouchdbSsmConfig
)
