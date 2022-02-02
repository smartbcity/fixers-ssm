package ssm.couchdb.f2.query

import com.ibm.cloud.cloudant.v1.model.ChangesResult
import com.ibm.cloud.cloudant.v1.model.ChangesResultItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ssm.couchdb.client.CouchdbSsmClient
import ssm.couchdb.client.getDocType
import ssm.couchdb.dsl.model.DatabaseChanges
import ssm.couchdb.dsl.query.CouchdbDatabaseGetChangesQueryDTO
import ssm.couchdb.dsl.query.CouchdbDatabaseGetChangesQueryFunction
import ssm.couchdb.dsl.query.CouchdbDatabaseGetChangesQueryResult
import ssm.couchdb.dsl.query.CouchdbDatabaseGetChangesQueryResultDTO
import ssm.couchdb.f2.commons.chainCodeDbName

class CouchDbDatabaseGetChangesQueryFunctionImpl(
	private val couchdbClient: CouchdbSsmClient,
) : CouchdbDatabaseGetChangesQueryFunction {

	override suspend fun invoke(msg: Flow<CouchdbDatabaseGetChangesQueryDTO>):
			Flow<CouchdbDatabaseGetChangesQueryResultDTO> = msg.map { payload ->
		try {
			getChanges(couchdbClient, payload)
		} catch (e: Exception) {
			CouchdbDatabaseGetChangesQueryResult(
				items = emptyList(),
				lastEventId = null
			)
		}
	}

	private fun getChanges(
		couchdbClient: CouchdbSsmClient,
		payload: CouchdbDatabaseGetChangesQueryDTO
	): CouchdbDatabaseGetChangesQueryResultDTO {
		return couchdbClient.getChanges(
			chainCodeDbName(payload.channelId, payload.chaincodeId),
			payload.lastEventId,
		).let { result ->
			transformResult(result, payload)
		}
	}

	private fun transformResult(
		result: ChangesResult,
		payload: CouchdbDatabaseGetChangesQueryDTO
	): CouchdbDatabaseGetChangesQueryResult {
		return result.results.filter { changes ->
			changes.parseId().isNotBlank()
		}.map { changesItem ->
			DatabaseChanges(
				objectId = changesItem.parseId(),
				docType = changesItem.getDocType(),
				changeEventId = changesItem.seq
			)
		}.filter { changes ->
			payload.docType?.name.isNullOrBlank() || changes.docType?.name == payload.docType?.name
		}.filter { changes ->
			changes.isValidId(payload.ssmName) || changes.isValidId(payload.sessionName)
		}.let {
			CouchdbDatabaseGetChangesQueryResult(
				items = it,
				lastEventId = result.lastSeq
			)
		}
	}

	private fun ChangesResultItem.parseId(): String {
		return id.substringAfter("_")
	}
	private fun DatabaseChanges.isValidId(id: String?): Boolean {
		return id.isNullOrBlank() || objectId == id
	}

}
