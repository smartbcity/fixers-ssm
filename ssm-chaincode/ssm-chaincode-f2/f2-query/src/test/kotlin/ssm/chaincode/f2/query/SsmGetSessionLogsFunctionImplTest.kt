package ssm.chaincode.f2.query

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

internal class SsmGetSessionLogsFunctionImplTest: FunctionTestBase() {

	@Test
	fun `must return function ssmGetSessionLogsFunction`() {
		val fnc: Any = catalog.lookup(SsmGetSessionLogsQueryFunctionImpl::ssmGetSessionLogsQueryFunction.name)
		Assertions.assertThat(fnc).isNotNull
	}
}