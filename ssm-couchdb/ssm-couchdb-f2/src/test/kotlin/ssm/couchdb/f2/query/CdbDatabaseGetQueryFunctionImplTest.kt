package ssm.couchdb.f2.query

import f2.dsl.fnc.invoke
import kotlinx.coroutines.runBlocking
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import ssm.couchdb.dsl.query.CdbDatabaseGetQuery
import ssm.couchdb.dsl.query.CdbDatabaseGetQueryFunction

internal class CdbDatabaseGetQueryFunctionImplTest : FunctionTestBase() {

	private val dbConfig = "sandbox_ssm"
	private val ssmName = "sandbox_ssm"

	@Autowired
	lateinit var cdbDatabaseGetQueryFunction: CdbDatabaseGetQueryFunction

	@Test
	fun `must return cdbGetDatabasesFunction from catalog`() {
		val fnc: Any = catalog.lookup("cdbDatabaseGetQueryFunction")
		Assertions.assertThat(fnc).isNotNull
	}


	@Test
	fun `must return all databases`(): Unit = runBlocking {
		val db = cdbDatabaseGetQueryFunction.invoke(
			CdbDatabaseGetQuery(
				dbName = ssmName, dbConfig = dbConfig
			)
		)

		Assertions.assertThat(db).isNotNull
	}
}