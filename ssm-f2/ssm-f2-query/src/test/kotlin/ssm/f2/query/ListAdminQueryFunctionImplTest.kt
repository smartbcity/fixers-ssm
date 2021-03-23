package ssm.f2.query

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class ListAdminQueryFunctionImplTest : FunctionTestBase() {

	@Test
	fun `must return function listAdminQueryFunction`() {
		val fnc: Any = catalog.lookup("listAdminQueryFunction")
		Assertions.assertThat(fnc).isNotNull
	}
}