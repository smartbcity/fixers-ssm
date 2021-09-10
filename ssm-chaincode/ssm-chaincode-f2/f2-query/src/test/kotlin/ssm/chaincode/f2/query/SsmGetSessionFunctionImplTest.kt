package ssm.chaincode.f2.query

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

internal class SsmGetSessionFunctionImplTest : FunctionTestBase() {

	@Test
	fun `must return function ssmGetSessionFunction`() {
		val fnc: Any = catalog.lookup(SsmGetSessionFunctionImpl::ssmGetSessionQueryFunction.name)
		Assertions.assertThat(fnc).isNotNull
	}
}