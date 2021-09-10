package ssm.couchdb.autoconfiguration

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class SsmCouchdbAutoconfiguration {

	@Bean
	@ConfigurationProperties(prefix = "ssm.couchdb")
	fun dbMap(): Map<String, SsmCouchdbProperties> {
		return HashMap()
	}
}
