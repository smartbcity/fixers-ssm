package ssm.api.features.query

import f2.dsl.fnc.invoke
import kotlinx.coroutines.runBlocking
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import ssm.data.bdd.TestConfig
import ssm.data.dsl.features.query.DataSsmListQuery

class DataSsmListQueryFunctionImpTest {

	private val function = DataSsmListQueryFunctionImp(
		TestConfig.proudhon
	).dataSsmListQueryFunction()


	@Disabled
	@Test
	fun `must return result`() = runBlocking<Unit> {
		val response = function.invoke(DataSsmListQuery(bearerToken = null, emptyList()))
		Assertions.assertThat(response.list).hasSize(2)
	}
}