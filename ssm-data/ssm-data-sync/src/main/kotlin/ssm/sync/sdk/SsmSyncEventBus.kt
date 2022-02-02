package ssm.sync.sdk

import f2.dsl.fnc.invokeWith
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Delay
import kotlinx.coroutines.cancel
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.transformLatest
import ssm.chaincode.dsl.model.SessionName
import ssm.chaincode.dsl.model.SsmName
import ssm.chaincode.dsl.model.uri.ChaincodeUri
import ssm.couchdb.dsl.model.ChangeEventId

class SsmSyncEventBus (
	private val chaincodeUri: ChaincodeUri,
	private val ssmName: SsmName? = null,
	private val sessionName: SessionName? = null,
	private val syncSsmF2Impl: SyncSsmCommandFunction
) {

	val eventBus: EventBus<SyncSsmCommandResult> = EventBus()
	fun sync(lastEventId: ChangeEventId? = null, delay: Long = 5000): Flow<SyncSsmCommandResult> = flow {
			var changeEventId = lastEventId
			while (true) {
				SyncSsmCommand(
					lastEventId = changeEventId,
					chaincodeUri = chaincodeUri
				).invokeWith(syncSsmF2Impl).let { result ->
					changeEventId = result.lastEventId
					emit(result)
				}
				delay(delay)
			}
	}


}
