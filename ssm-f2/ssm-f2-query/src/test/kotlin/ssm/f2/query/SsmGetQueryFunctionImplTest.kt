package ssm.f2.query

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class SsmGetQueryFunctionImplTest : FunctionTestBase() {

	@Test
	fun `must return function ssmGetQueryFunction`() {
		val fnc: Any = catalog.lookup("ssmGetQueryFunction")
		Assertions.assertThat(fnc).isNotNull
	}
}