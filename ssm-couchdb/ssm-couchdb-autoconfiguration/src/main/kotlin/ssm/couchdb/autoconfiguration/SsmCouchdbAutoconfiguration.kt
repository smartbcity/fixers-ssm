package ssm.couchdb.autoconfiguration

import com.ibm.cloud.cloudant.v1.Cloudant
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import ssm.couchdb.client.SsmCouchDbClient
import ssm.couchdb.client.builder.SsmCouchDbBasicAuth

@Configuration
@EnableConfigurationProperties(SsmCouchdbProperties::class)
class SsmCouchdbAutoconfiguration {

	@Bean
	fun ssmCouchDbClient(properties: SsmCouchdbProperties): SsmCouchDbClient {
		return SsmCouchDbClient.builder()
			.withUrl(properties.url)
			.withName(properties.serviceName)
			.withAuth(SsmCouchDbBasicAuth(
				username = properties.username,
				password = properties.password,
			)).build()
	}

	@Bean
	fun client(ssmCouchDbClient: SsmCouchDbClient): Cloudant = ssmCouchDbClient.cloudant
}