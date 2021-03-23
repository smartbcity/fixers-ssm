package ssm.f2.query

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class SsmGetSessionFunctionImplTest : FunctionTestBase() {

	@Test
	fun `must return function ssmGetSessionFunction`() {
		val fnc: Any = catalog.lookup("ssmGetSessionFunction")
		Assertions.assertThat(fnc).isNotNull
	}
}