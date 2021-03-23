package ssm.f2.query

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class SsmListUserQueryFunctionImplTest : FunctionTestBase() {

	@Test
	fun `must return function ssmListUserQueryFunction`() {
		val fnc: Any = catalog.lookup("ssmListUserQueryFunction")
		Assertions.assertThat(fnc).isNotNull
	}
}