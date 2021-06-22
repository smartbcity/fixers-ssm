package ssm.couchdb.f2.query

import f2.dsl.function.F2Function
import f2.function.spring.invokeSingle
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import ssm.couchdb.dsl.query.SsmCdbGetDatabasesQuery

internal class CdbGetDatabasesFunctionImplTest : FunctionTestBase() {

	private val ssmName = "sandbox_ssm"

	@Autowired
	lateinit var cdbGetDatabasesFunction: F2Function<SsmCdbGetDatabasesQuery, Flow<String>>

	@Test
	fun `must return cdbGetDatabasesFunction from catalog`() {
		val fnc: Any = catalog.lookup("cdbGetDatabasesFunction")
		Assertions.assertThat(fnc).isNotNull
	}


	@Test
	fun `must return all databases`(): Unit = runBlocking {
		val db = cdbGetDatabasesFunction.invokeSingle(
			SsmCdbGetDatabasesQuery(
				ssmName,
			)
		).toList()

		Assertions.assertThat(db).isNotEmpty
	}
}