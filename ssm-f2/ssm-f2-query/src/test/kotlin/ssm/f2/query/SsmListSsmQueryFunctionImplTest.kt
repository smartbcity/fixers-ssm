package ssm.f2.query

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class SsmListSsmQueryFunctionImplTest : FunctionTestBase() {

	@Test
	fun `must return function ssmListSsmQueryFunction`() {
		val fnc: Any = catalog.lookup("ssmListSsmQueryFunction")
		Assertions.assertThat(fnc).isNotNull
	}
}