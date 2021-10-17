package ssm.sync.sdk

import f2.dsl.fnc.invoke
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test
import sdk.TestConfig

internal class SsmSyncF2Test {

	private val syncSsmCommandFunction = SsmSyncF2(TestConfig.proudhon()).syncSsmCommandFunction()

	@Test
	fun syncSsmCommandFunction() = runBlocking<Unit> {
		syncSsmCommandFunction(SyncSsmCommand(
			lastEventId = null,
			chaincode = ""
		))
	}
}