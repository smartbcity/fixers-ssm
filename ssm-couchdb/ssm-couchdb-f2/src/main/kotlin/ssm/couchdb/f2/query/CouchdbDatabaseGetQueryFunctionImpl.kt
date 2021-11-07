package ssm.couchdb.f2.query

import com.ibm.cloud.cloudant.v1.model.DatabaseInformation
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ssm.couchdb.client.SsmCouchdbClient
import ssm.couchdb.dsl.model.Database
import ssm.couchdb.dsl.query.CouchdbDatabaseGetQueryDTO
import ssm.couchdb.dsl.query.CouchdbDatabaseGetQueryFunction
import ssm.couchdb.dsl.query.CouchdbDatabaseGetQueryResultDTO
import ssm.couchdb.dsl.query.CouchdbSsmDatabaseGetQueryResult
import ssm.couchdb.f2.commons.chainCodeDbName

class CouchdbDatabaseGetQueryFunctionImpl(
	private val couchdbClient: SsmCouchdbClient,
) : CouchdbDatabaseGetQueryFunction {

	private fun DatabaseInformation.asDatabase() = Database(this.dbName)

	override suspend fun invoke(msg: Flow<CouchdbDatabaseGetQueryDTO>): Flow<CouchdbDatabaseGetQueryResultDTO> = msg.map { payload ->
		try {
			CouchdbSsmDatabaseGetQueryResult(
				item = couchdbClient.getDatabase(chainCodeDbName(payload.channelId, payload.chaincodeId)).asDatabase()
			)
		} catch (e: Exception) {
			e.printStackTrace()
			CouchdbSsmDatabaseGetQueryResult(
				item = null
			)
		}
	}
}
