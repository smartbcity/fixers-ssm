package ssm.couchdb.f2.query

import ssm.couchdb.client.SsmCouchDbClient
import ssm.couchdb.client.getDocType
import ssm.couchdb.dsl.config.SsmCouchdbConfig
import ssm.couchdb.dsl.model.DatabaseChanges
import ssm.couchdb.dsl.query.CouchDbDatabaseGetChangesQueryFunction
import ssm.couchdb.dsl.query.CouchdbDatabaseGetChangesQueryDTO
import ssm.couchdb.dsl.query.CouchdbDatabaseGetChangesQueryResultDTO
import ssm.couchdb.dsl.query.CouchdbSsmDatabaseGetChangesQueryResult
import ssm.couchdb.f2.commons.CouchdbF2Function
import ssm.couchdb.f2.commons.chainCodeDbName

class CouchDbDatabaseGetChangesQueryFunctionImpl(
	private val config: SsmCouchdbConfig,
) {

	fun couchDbDatabaseGetChangesQueryFunction(): CouchDbDatabaseGetChangesQueryFunction =
		CouchdbF2Function.function(config) { cmd, couchdbClient ->
			try {
				getChanges(couchdbClient, cmd)
			} catch (e: Exception) {
				CouchdbSsmDatabaseGetChangesQueryResult(
					items = emptyList()
				)
			}
		}

	private fun getChanges(
		couchdbClient: SsmCouchDbClient,
		cmd: CouchdbDatabaseGetChangesQueryDTO
	): CouchdbDatabaseGetChangesQueryResultDTO {
		return couchdbClient.getChanges(
			chainCodeDbName(cmd.channelId, cmd.chaincodeId),
			cmd.lastEventId
		).let { result ->
			CouchdbSsmDatabaseGetChangesQueryResult(
				items = result.results.map { changesItem ->
					DatabaseChanges(
						objectId = changesItem.id,
						docType = changesItem.getDocType(),
						changeEventId = changesItem.seq
					)
				}
			)
		}
	}
}
