package ssm.couchdb.f2

import f2.function.spring.invokeSingle
import kotlinx.coroutines.runBlocking
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import ssm.couchdb.client.SsmCouchDbClient
import ssm.couchdb.dsl.query.CdbGetSsmListQuery
import ssm.couchdb.dsl.query.CdbGetSsmListQueryFunction
import ssm.dsl.DocType

internal class CdbGetSsmListQueryFunctionImplTest : FunctionTestBase() {

	@Autowired
	lateinit var cdbGetSsmListQueryFunction: CdbGetSsmListQueryFunction

	@Autowired
	lateinit var ssmCouchDbClient: SsmCouchDbClient

	companion object {
		const val DB_CONF = "test"
		const val DB_NAME = "proudhon_ssm"
//		const val DB_NAME = "sandbox_ssm"
	}

	@Test
	fun `must return cdbGetSsmListQueryFunction from catalog`() {
		val fnc: Any = catalog.lookup("cdbGetSsmListQueryFunction")
		Assertions.assertThat(fnc).isNotNull
	}


	@Test
	fun `must return all ssms`(): Unit = runBlocking {
		val expectedSsmListSize = ssmCouchDbClient.getCount(DB_NAME, DocType.Ssm)

		val command = CdbGetSsmListQuery(DB_NAME, DB_CONF)
		val ssmList = cdbGetSsmListQueryFunction.invokeSingle(command).ssmList

		Assertions.assertThat(ssmList.size).isEqualTo(expectedSsmListSize)
	}
}