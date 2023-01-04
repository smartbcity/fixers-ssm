package ssm.couchdb.spring.autoconfigure

import org.springframework.boot.context.properties.ConfigurationProperties
import ssm.couchdb.dsl.config.CouchdbSsmConfig

@ConfigurationProperties(prefix = "ssm")
data class CouchdbSsmProperties(
	val couchdb: CouchdbSsmConfig
)
