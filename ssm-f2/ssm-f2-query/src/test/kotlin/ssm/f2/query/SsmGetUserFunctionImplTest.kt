package ssm.f2.query

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

internal class SsmGetUserFunctionImplTest : FunctionTestBase() {

	@Test
	fun `must return function ssmGetUserFunction`() {
		val fnc: Any = catalog.lookup("ssmGetUserFunction")
		Assertions.assertThat(fnc).isNotNull
	}
}