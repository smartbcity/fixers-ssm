package ssm.api.features.query

import f2.dsl.fnc.invokeWith
import kotlinx.coroutines.runBlocking
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import ssm.api.DataSsmQueryFunctionImpl
import ssm.data.bdd.TestConfig
import ssm.data.dsl.features.query.DataSsmSessionListQuery
import ssm.data.dsl.features.query.DataSsmSessionListQueryFunction

internal class DataSsmSessionListQueryFunctionImplTest {

	private val dataSsmQueryFunction = DataSsmQueryFunctionImpl(
		TestConfig.proudhon,
	)

	private val function: DataSsmSessionListQueryFunction = dataSsmQueryFunction.dataSsmSessionListQueryFunction()

	@Test
	fun `test exception`() = runBlocking<Unit> {
		val result = DataSsmSessionListQuery(
			ssm = "ssm:peer0:proudhon:ssm:Delivery",
		).invokeWith(function)
		Assertions.assertThat(result.items).isNotEmpty
	}
}
