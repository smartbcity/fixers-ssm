package ssm.couchdb.client

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import ssm.couchdb.client.builder.SsmCouchDbBasicAuth

class CouchDbSsmServiceTest {

	private val ssmName = "marius_ssm"
	private val username = "admin"
	private val password = "smartb"
	private val serviceUrl = "http://peer1.pr-commune.smartb.network:5984"

	private var couchDbSsmService: SsmCouchDbClient = SsmCouchDbClient.builder()
		.withUrl(serviceUrl)
		.withName("Ssm Sdk Test")
		.withAuth(SsmCouchDbBasicAuth(
			username = this.username,
			password = this.password,
		)).build()

	@Test
	fun shouldReturnAdmin() {
		val admin = couchDbSsmService.getAllAdmins(ssmName)
		Assertions.assertThat(admin).isNotNull
	}

	@Test
	fun shouldReturnSsm() {
		val ssms = couchDbSsmService.getAllSsm(ssmName)
		Assertions.assertThat(ssms).isNotNull
	}

//	@Test
//	fun shouldReturnStates() {
//		val states = couchDbSsmService.getAllStates(ssmName)
//		Assertions.assertThat(states).isNotNull
//	}
}


//{
//	"_id": "ADMIN_adrien@smartb.city",
//	"_rev": "2-7e71939e64984b48d86201332f6a4544",
//	"docType": "admin",
//	"name": "adrien@smartb.city",
//	"pub": "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAwPlqAYRVJwio3c4Cuo/Ncs9TdLr6OGV/3kOmgHgQV9kuK2fFqXXgDfmfQsdayvhvXa0oLYLwCdbly0dEqJsLzXyn3Sm+DEsA186+9USkRquq5cxN+iJRxv0X5+UvVAZ+N1LvCZKT6zzzKKA6NgZZb5f/7uebC2f8uavkqDbj4RZE9GCH9RHE3ca21nEbAYfACBJEAx5t3YcfCWuzLj0EjN0UH6VCbFD9ksET4Gk8lyhjhDl7HmMcRMea3KyBJ60s5e886p/XbGWhoeAyqJH8xjWjzVo6Fa6iHJJtw/G4fHHiMxP4eg64mWrQEYPdg6i481cC03lJ+Qd3K6R31aZKMQIDAQAB",
//	"~version": "CgMBBgA="
//}