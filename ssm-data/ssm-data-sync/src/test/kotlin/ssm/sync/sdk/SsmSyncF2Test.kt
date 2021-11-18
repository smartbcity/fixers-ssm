package ssm.sync.sdk

import f2.dsl.fnc.invokeWith
import kotlinx.coroutines.runBlocking
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import ssm.chaincode.dsl.model.uri.ChaincodeUri
import ssm.data.bdd.TestConfig

internal class SsmSyncF2Test {
	private val syncSsmCommandFunction = SsmSyncF2Builder.build(TestConfig.local)

	@Test
	fun syncSsmCommandFunction() = runBlocking<Unit> {
		val items = SyncSsmCommand(
			lastEventId = null,
			chaincodeUri =  ChaincodeUri("chaincode:sandbox:ssm")
		).invokeWith(syncSsmCommandFunction).items
		Assertions.assertThat(items).isNotNull
	}
}