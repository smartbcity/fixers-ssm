package ssm.couchdb.f2

import f2.function.spring.invokeSingle
import kotlinx.coroutines.runBlocking
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import ssm.coucbdb.dsl.query.CdbGetSsmSessionListQuery
import ssm.coucbdb.dsl.query.CdbGetSsmSessionListQueryFunction
import ssm.couchdb.client.SsmCouchDbClient
import ssm.dsl.DocType

internal class CdbGetSsmSessionListQueryFunctionImplTest : FunctionTestBase() {

	@Autowired
	lateinit var cdbGetSsmSessionListQueryFunction: CdbGetSsmSessionListQueryFunction

	@Autowired
	lateinit var ssmCouchDbClient: SsmCouchDbClient

	companion object {
		const val DB_NAME = "sandbox_ssm"
	}

	@Test
	fun `must return cdbGetSsmSessionListQueryFunction from catalog`() {
		val fnc: Any = catalog.lookup("cdbGetSsmSessionListQueryFunction")
		Assertions.assertThat(fnc).isNotNull
	}


	@Test
	fun `must return all sessions`(): Unit = runBlocking {
		val expectedSsmSessionListSize = ssmCouchDbClient.getCount(DB_NAME, DocType.State)

		val command = CdbGetSsmSessionListQuery(DB_NAME, null)
		val sessions = cdbGetSsmSessionListQueryFunction.invokeSingle(command).sessions

		Assertions.assertThat(sessions.size).isEqualTo(expectedSsmSessionListSize)
	}
}