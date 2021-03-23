package ssm.f2.query

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class ListUserQueryFunctionImplTest : FunctionTestBase() {

	@Test
	fun `must return function listUserQueryFunction`() {
		val fnc: Any = catalog.lookup("listUserQueryFunction")
		Assertions.assertThat(fnc).isNotNull
	}
}