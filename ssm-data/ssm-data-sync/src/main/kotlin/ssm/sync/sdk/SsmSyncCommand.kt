package ssm.sync.sdk

import f2.dsl.fnc.F2Function
import ssm.chaincode.dsl.model.uri.ChaincodeUri
import ssm.couchdb.dsl.model.ChangeEventId

typealias SyncSsmCommandFunction = F2Function<SyncSsmCommand, SyncSsmCommandResult>

interface SyncSsmCommandDTO {
	val lastEventId: ChangeEventId?
	val chaincode: ChaincodeUri
}

interface SyncSsmCommandResultDTO {
	val lastEventId: String
	val userSynced: String
	val ssmSynced: String
	val stateSynced: String
}

class SyncSsmCommand(
	override val lastEventId: ChangeEventId?,
	override val chaincode: ChaincodeUri
) : SyncSsmCommandDTO

class SyncSsmCommandResult(
	override val lastEventId: String,
	override val userSynced: String,
	override val ssmSynced: String,
	override val stateSynced: String
) : SyncSsmCommandResultDTO
