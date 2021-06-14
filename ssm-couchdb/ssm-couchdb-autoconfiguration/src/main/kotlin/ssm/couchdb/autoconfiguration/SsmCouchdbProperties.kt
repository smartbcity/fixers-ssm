package ssm.couchdb.autoconfiguration

import org.springframework.boot.context.properties.ConstructorBinding

@ConstructorBinding
data class SsmCouchdbProperties(
    val url: String,
    val username: String,
    val password: String,
    val serviceName: String,
)
