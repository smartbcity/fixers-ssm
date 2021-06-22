package ssm.couchdb.f2.query

import kotlinx.coroutines.runBlocking
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import ssm.couchdb.dsl.query.CdbSsmSessionListQuery
import ssm.couchdb.dsl.query.CdbGetSsmSessionListQueryFunction

internal class CdbGetSsmSessionListQueryFunctionImplTest : FunctionTestBase() {

	@Autowired
	lateinit var cdbGetSsmSessionListQueryFunction: CdbGetSsmSessionListQueryFunction

//	@Autowired
//	lateinit var ssmCouchDbClient: SsmCouchDbClient

	companion object {
		const val DB_CONF = "sandbox_ssm"
		const val DB_NAME = "sandbox_ssm"
	}

	@Test
	fun `must return cdbGetSsmSessionListQueryFunction from catalog`() {
		val fnc: Any = catalog.lookup("cdbGetSsmSessionListQueryFunction")
		Assertions.assertThat(fnc).isNotNull
	}


	@Test
	fun `must return all sessions`(): Unit = runBlocking {
//		val expectedSsmSessionListSize = ssmCouchDbClient.getCount(DB_NAME, DocType.State)

		val command = CdbSsmSessionListQuery(DB_NAME, null, DB_CONF)
		val sessions = cdbGetSsmSessionListQueryFunction.invokeSingle(command).sessions

		Assertions.assertThat(sessions).isNotEmpty
	}
}