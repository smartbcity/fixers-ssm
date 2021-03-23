package ssm.f2.query

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class SsmListSessionQueryFunctionImplTest : FunctionTestBase() {

	@Test
	fun `must return function ssmListSessionQueryFunction`() {
		val fnc: Any = catalog.lookup("ssmListSessionQueryFunction")
		Assertions.assertThat(fnc).isNotNull
	}
}