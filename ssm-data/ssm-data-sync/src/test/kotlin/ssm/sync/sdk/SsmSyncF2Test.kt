package ssm.sync.sdk

import f2.dsl.fnc.invokeWith
import kotlinx.coroutines.flow.cancellable
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import ssm.bdd.config.SsmBddConfig
import ssm.chaincode.dsl.model.uri.ChaincodeUri

internal class SsmSyncF2Test {
	private val syncSsmCommandFunction = SsmSyncF2Builder.build(SsmBddConfig.Data.config)

	@Test
	fun syncSsmCommandFunction(): Unit = runBlocking {
		val result = SyncSsmCommand(
			lastEventId = null,
			chaincodeUri =  ChaincodeUri("chaincode:sandbox:ssm")
		).invokeWith(syncSsmCommandFunction)

		Assertions.assertThat(result.items).isNotEmpty

		val resultLast = SyncSsmCommand(
			lastEventId = result.lastEventId,
			chaincodeUri =  ChaincodeUri("chaincode:sandbox:ssm")
		).invokeWith(syncSsmCommandFunction)

		Assertions.assertThat(resultLast.items).isEmpty()
	}

	@Test
	fun ssmSyncEventBus() = runTest {
		val bus = SsmSyncEventBus(
			chaincodeUri = ChaincodeUri("chaincode:sandbox:ssm"),
			syncSsmF2Impl = syncSsmCommandFunction,
		)

		bus.sync(delay = 10000).take(5).toList().let { sync ->
			Assertions.assertThat(sync.get(0).items).isNotEmpty
			Assertions.assertThat(sync.get(1).items).isEmpty()
			Assertions.assertThat(sync.get(2).items).isEmpty()
			Assertions.assertThat(sync.get(3).items).isEmpty()
			Assertions.assertThat(sync.get(4).items).isEmpty()
		}
	}
}