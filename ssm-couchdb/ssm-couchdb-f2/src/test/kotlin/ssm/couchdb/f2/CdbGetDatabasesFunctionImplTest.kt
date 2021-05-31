package ssm.couchdb.f2

import f2.dsl.function.F2Supplier
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

internal class CdbGetDatabasesFunctionImplTest : FunctionTestBase() {

	@Autowired
	lateinit var cdbGetDatabasesFunction: F2Supplier<String>

	@Test
	fun `must return cdbGetDatabasesFunction from catalog`() {
		val fnc: Any = catalog.lookup("cdbGetDatabasesFunction")
		Assertions.assertThat(fnc).isNotNull
	}


	@Test
	fun `must return all databases`(): Unit = runBlocking {
		val db = cdbGetDatabasesFunction.invoke().toList()

		Assertions.assertThat(db).isNotEmpty
	}
}