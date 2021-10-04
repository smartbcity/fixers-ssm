package ssm.couchdb.client.test

import ssm.couchdb.client.SsmCouchDbClient
import ssm.couchdb.client.builder.SsmCouchDbBasicAuth

object DataTest {
	val ssmName = "sandbox_ssm"

	private val username = "couchdb"
	private val password = "couchdb"
	private val serviceUrl = "http://localhost:5984"

	var ssmCouchDbClient: SsmCouchDbClient = SsmCouchDbClient.builder()
		.withUrl(serviceUrl)
		.withName("Ssm Sdk Unit Test")
		.withAuth(
			SsmCouchDbBasicAuth(
			username = username,
			password = password,
		)
		).build()
}