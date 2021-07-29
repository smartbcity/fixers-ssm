package ssm.couchdb.f2.query

import f2.function.spring.invokeSingle
import kotlinx.coroutines.runBlocking
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import ssm.couchdb.dsl.query.CdbDatabaseListQuery
import ssm.couchdb.dsl.query.CdbDatabaseListQueryFunction

class CdbDatabaseListQueryFunctionImplTest : FunctionTestBase() {

	private val ssmName = "sandbox_ssm"

	@Autowired
	lateinit var cdbDatabaseListQueryFunction: CdbDatabaseListQueryFunction

	@Test
	fun `must return cdbGetDatabaseFunction from catalog`() {
		val fnc: Any = catalog.lookup("cdbDatabaseListQueryFunction")
		Assertions.assertThat(fnc).isNotNull
	}

	@Test
	fun `must return one database by name`(): Unit = runBlocking {
		val db = cdbDatabaseListQueryFunction.invokeSingle(CdbDatabaseListQuery(ssmName))
		Assertions.assertThat(db).isNotNull
	}
}