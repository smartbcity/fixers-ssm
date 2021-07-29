package ssm.couchdb.f2.query

import f2.function.spring.invokeSingle
import kotlinx.coroutines.runBlocking
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import ssm.couchdb.dsl.query.CdbSsmListQuery
import ssm.couchdb.dsl.query.CdbSsmListQueryFunction

internal class CdbSsmListQueryFunctionImplTest : FunctionTestBase() {

	@Autowired
	lateinit var cdbSsmListQueryFunction: CdbSsmListQueryFunction

//	@Autowired
//	lateinit var ssmCouchDbClient: SsmCouchDbClient

	companion object {
		const val DB_CONF = "sandbox_ssm"
		const val DB_NAME = "sandbox_ssm"
//		const val DB_NAME = "proudhon_ssm"
	}

	@Test
	fun `must return cdbGetSsmListQueryFunction from catalog`() {
		val fnc: Any = catalog.lookup("cdbSsmListQueryFunction")
		Assertions.assertThat(fnc).isNotNull
	}


	@Test
	fun `must return all ssm`(): Unit = runBlocking {
		val command = CdbSsmListQuery(DB_NAME, DB_CONF)
		val ssmList = cdbSsmListQueryFunction.invokeSingle(command).ssmList

		Assertions.assertThat(ssmList).isNotEmpty
	}
}