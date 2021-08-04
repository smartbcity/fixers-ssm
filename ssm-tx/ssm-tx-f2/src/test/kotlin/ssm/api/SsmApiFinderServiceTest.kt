package ssm.api

import f2.dsl.fnc.invoke
import kotlinx.coroutines.runBlocking
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.springframework.beans.factory.annotation.Autowired
import ssm.tx.dsl.features.query.TxSsmListQuery
import ssm.tx.dsl.features.query.TxSsmListQueryFunction

internal class SsmApiFinderServiceTest : FunctionTestBase() {

	@Autowired
	lateinit var txSsmListQueryFunction: TxSsmListQueryFunction

	@Test
	fun txSsmListQueryFunction(): Unit = runBlocking {
		val ssms = txSsmListQueryFunction.invoke(TxSsmListQuery())
		Assertions.assertThat(ssms.list).isNotEmpty
	}
}