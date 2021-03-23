package ssm.f2.query

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

internal class ListSsmSessionQueryFunctionImplTest : FunctionTestBase() {

	@Test
	fun `must return function listSsmSessionQueryFunction`() {
		val fnc: Any = catalog.lookup("listSsmSessionQueryFunction")
		Assertions.assertThat(fnc).isNotNull
	}
}