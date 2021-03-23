package ssm.f2.query

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class GetSsmAdminFunctionImplTest : FunctionTestBase() {

	@Test
	fun `must return function getSsmAdminFunction`() {
		val fnc: Any = catalog.lookup("getSsmAdminFunction")
		Assertions.assertThat(fnc).isNotNull
	}
}