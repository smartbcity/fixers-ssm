package ssm.f2.query

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class SsmGetAdminFunctionImplTest : FunctionTestBase() {

	@Test
	fun `must return function ssmGetAdminFunction`() {
		val fnc: Any = catalog.lookup("ssmGetAdminFunction")
		Assertions.assertThat(fnc).isNotNull
	}
}