package ssm.f2.query

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class ListSsmQueryFunctionImplTest : FunctionTestBase() {

	@Test
	fun `must return function listSsmQueryFunction`() {
		val fnc: Any = catalog.lookup("listSsmQueryFunction")
		Assertions.assertThat(fnc).isNotNull
	}
}