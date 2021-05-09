package ssm.couchdb.f2

import com.ibm.cloud.cloudant.v1.model.DatabaseInformation
import f2.dsl.function.F2Function
import f2.function.spring.invokeSingle
import kotlinx.coroutines.runBlocking
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

internal class CdbGetDatabaseFunctionImplTest : FunctionTestBase() {

	@Autowired
	lateinit var cdbGetDatabaseFunction: F2Function<String, DatabaseInformation>

	@Test
	fun `must return cdbGetDatabaseFunction from catalog`() {
		val fnc: Any = catalog.lookup("cdbGetDatabaseFunction")
		Assertions.assertThat(fnc).isNotNull
	}

	@Test
	fun `must return one database by name`(): Unit = runBlocking {
		val db = cdbGetDatabaseFunction.invokeSingle("blanqui_ssm")
		Assertions.assertThat(db).isNotNull
	}
}