package ssm.couchdb.client.builder

import com.ibm.cloud.cloudant.v1.Cloudant
import com.ibm.cloud.sdk.core.security.BasicAuthenticator
import ssm.couchdb.client.SsmCouchdbClient
import ssm.sdk.json.JSONConverter
import ssm.sdk.json.JSONConverterObjectMapper

class SsmCouchDbClientBuilder {

	private var jsonConverter: JSONConverter = JSONConverterObjectMapper()
	private lateinit var auth: SsmCouchDbAuth
	private lateinit var url: String
	private lateinit var name: String

	fun withJSONConverter(jsonConverter: JSONConverter): SsmCouchDbClientBuilder = apply {
		this.jsonConverter = jsonConverter
	}

	fun withUrl(url: String): SsmCouchDbClientBuilder = apply {
		this.url = url
	}

	fun withName(name: String): SsmCouchDbClientBuilder = apply {
		this.name = name
	}

	fun withAuth(auth: SsmCouchDbAuth): SsmCouchDbClientBuilder = apply {
		this.auth = auth
	}

	fun build(): SsmCouchdbClient {
		val converter = requireNotNull(this.jsonConverter)
		val auth = requireNotNull(this.auth)
		val cloudant = buildCloudant(auth)
		return SsmCouchdbClient(cloudant, converter)
	}

	private fun buildCloudant(auth: SsmCouchDbAuth): Cloudant {
		val cloudantAuth = when (auth) {
			is SsmCouchDbBasicAuth -> BasicAuthenticator.Builder()
				.username(auth.username)
				.password(auth.password)
				.build()
		}

		val client = Cloudant(name, cloudantAuth).apply {
			serviceUrl = url
		}

		val serverInformation = client
			.serverInformation
			.execute()
			.result
		println("Server Version: ${serverInformation.version}")
		return client
	}
}
