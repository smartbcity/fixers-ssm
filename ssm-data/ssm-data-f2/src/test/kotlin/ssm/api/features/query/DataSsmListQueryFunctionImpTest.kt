package ssm.api.features.query

import f2.dsl.fnc.invoke
import kotlinx.coroutines.runBlocking
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import ssm.api.DataSsmQueryFunctionImpl
import ssm.data.bdd.TestConfig
import ssm.data.dsl.features.query.DataSsmListQuery

class DataSsmListQueryFunctionImpTest {
	private val dataSsmQueryFunction = DataSsmQueryFunctionImpl(
		TestConfig.proudhon,
	)

	private val function = dataSsmQueryFunction.dataSsmListQueryFunction()

	@Disabled
	@Test
	fun `must return result`() = runBlocking<Unit> {
		val response = function.invoke(DataSsmListQuery(emptyList()))
		Assertions.assertThat(response.items).hasSize(2)
	}
}
