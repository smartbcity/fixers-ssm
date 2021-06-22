package ssm.couchdb.f2.query

import com.ibm.cloud.cloudant.v1.model.DatabaseInformation
import f2.dsl.function.F2Function
import f2.function.spring.invokeSingle
import kotlinx.coroutines.runBlocking
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import ssm.couchdb.dsl.query.CdbSsmDatabaseGetQuery

class CdbGetDatabaseFunctionImplTest : FunctionTestBase() {

	private val ssmName = "sandbox_ssm"

	@Autowired
	lateinit var cdbGetDatabaseFunction: F2Function<CdbSsmDatabaseGetQuery, DatabaseInformation?>

	@Test
	fun `must return cdbGetDatabaseFunction from catalog`() {
		val fnc: Any = catalog.lookup("cdbGetDatabaseFunction")
		Assertions.assertThat(fnc).isNotNull
	}

	@Test
	fun `must return one database by name`(): Unit = runBlocking {
		val db = cdbGetDatabaseFunction.invokeSingle(CdbSsmDatabaseGetQuery(ssmName, ssmName))
		Assertions.assertThat(db).isNotNull
	}
}