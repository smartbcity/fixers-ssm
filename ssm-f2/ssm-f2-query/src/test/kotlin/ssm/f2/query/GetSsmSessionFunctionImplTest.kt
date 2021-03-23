package ssm.f2.query

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class GetSsmSessionFunctionImplTest : FunctionTestBase() {

	@Test
	fun `must return function getSsmSessionFunction`() {
		val fnc: Any = catalog.lookup("getSsmSessionFunction")
		Assertions.assertThat(fnc).isNotNull
	}
}