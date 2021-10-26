package ssm.sync.sdk

import f2.dsl.fnc.invoke
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.runBlocking
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import ssm.data.bdd.TestConfig

internal class SsmSyncF2Test {

	private val syncSsmCommandFunction = SsmSyncF2(TestConfig.proudhon).syncSsmCommandFunction()

	@Test
	fun syncSsmCommandFunction() = runBlocking<Unit> {
		val test = syncSsmCommandFunction.invoke(SyncSsmCommand(
			lastEventId = null,
			chaincode = ""
		))

		Assertions.assertThat(test).isNotNull
	}
}