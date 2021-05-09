package ssm.couchdb.autoconfiguration

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding


@ConstructorBinding
@ConfigurationProperties(prefix = "ssm.couchdb")
data class SsmCouchdbProperties(
	val url: String,
	val username: String,
	val password: String,
	val serviceName: String,
)