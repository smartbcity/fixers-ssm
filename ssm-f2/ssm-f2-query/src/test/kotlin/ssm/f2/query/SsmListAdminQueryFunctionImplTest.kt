package ssm.f2.query

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class SsmListAdminQueryFunctionImplTest : FunctionTestBase() {

	@Test
	fun `must return function ssmListAdminQueryFunction`() {
		val fnc: Any = catalog.lookup("ssmListAdminQueryFunction")
		Assertions.assertThat(fnc).isNotNull
	}
}