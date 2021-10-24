package ssm.api.features.query

import f2.dsl.fnc.invoke
import kotlinx.coroutines.runBlocking
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import ssm.data.bdd.TestConfig
import ssm.data.dsl.features.query.DataSsmSessionListQuery

internal class DataSsmSessionListQueryFunctionImplTest {
	private val function = DataSsmSessionListQueryFunctionImpl(
		TestConfig.proudhon
	).dataSsmSessionListQueryFunction()

	@Test
	fun `test exception`() = runBlocking<Unit> {
		val result = function.invoke(DataSsmSessionListQuery(
			ssm = "ssm:peer0:proudhon:ssm:Delivery",
		))
		Assertions.assertThat(result.list).isNotEmpty
	}
}
