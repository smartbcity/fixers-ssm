package ssm.couchdb.autoconfiguration

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import ssm.couchdb.client.SsmCouchDbClient
import ssm.couchdb.client.builder.SsmCouchDbBasicAuth

@Configuration
class SsmCouchdbAutoconfiguration {

	@Bean
	@ConfigurationProperties(prefix = "ssm.couchdb")
	fun dbMap(): Map<String, SsmCouchdbProperties> {
		return HashMap()
	}

	@Bean
	fun ssmCouchDbClients(): Map<String, SsmCouchDbClient> {
		return dbMap().mapValues { (_, config) ->
			SsmCouchDbClient.builder()
				.withUrl(config.url)
				.withName(config.serviceName)
				.withAuth(
					SsmCouchDbBasicAuth(
						username = config.username,
						password = config.password,
					)
				).build()
		}
	}
}

