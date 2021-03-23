package ssm.f2.query

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

internal class GetSsmUserFunctionImplTest : FunctionTestBase() {

	@Test
	fun `must return function getSsmUserFunction`() {
		val fnc: Any = catalog.lookup("getSsmUserFunction")
		Assertions.assertThat(fnc).isNotNull
	}
}