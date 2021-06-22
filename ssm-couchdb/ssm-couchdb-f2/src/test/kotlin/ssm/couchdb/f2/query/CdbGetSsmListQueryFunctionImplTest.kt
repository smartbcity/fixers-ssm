package ssm.couchdb.f2.query

import kotlinx.coroutines.runBlocking
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import ssm.couchdb.dsl.query.CdbSsmListQuery
import ssm.couchdb.dsl.query.CdbGetSsmListQueryFunction

internal class CdbGetSsmListQueryFunctionImplTest : FunctionTestBase() {

	@Autowired
	lateinit var cdbGetSsmListQueryFunction: CdbGetSsmListQueryFunction

//	@Autowired
//	lateinit var ssmCouchDbClient: SsmCouchDbClient

	companion object {
		const val DB_CONF = "sandbox_ssm"
		const val DB_NAME = "sandbox_ssm"
//		const val DB_NAME = "proudhon_ssm"
	}

	@Test
	fun `must return cdbGetSsmListQueryFunction from catalog`() {
		val fnc: Any = catalog.lookup("cdbGetSsmListQueryFunction")
		Assertions.assertThat(fnc).isNotNull
	}


	@Test
	fun `must return all ssm`(): Unit = runBlocking {
//		val expectedSsmListSize = ssmCouchDbClient.getCount(DB_NAME, DocType.Ssm)

		val command = CdbSsmListQuery(DB_NAME, DB_CONF)
		val ssmList = cdbGetSsmListQueryFunction.invokeSingle(command).ssmList

		Assertions.assertThat(ssmList).isNotEmpty
	}
}