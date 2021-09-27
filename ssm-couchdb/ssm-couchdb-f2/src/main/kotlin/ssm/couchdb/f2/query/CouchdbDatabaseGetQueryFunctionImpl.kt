package ssm.couchdb.f2.query

import com.ibm.cloud.cloudant.v1.model.DatabaseInformation
import ssm.couchdb.dsl.config.SsmCouchdbConfig
import ssm.couchdb.dsl.model.Database
import ssm.couchdb.dsl.query.CouchdbDatabaseGetQueryFunction
import ssm.couchdb.dsl.query.CouchdbSsmDatabaseGetQueryResult
import ssm.couchdb.f2.commons.CouchdbF2Function
import ssm.couchdb.f2.commons.chainCodeDbName

class CouchdbDatabaseGetQueryFunctionImpl(
	private val config: SsmCouchdbConfig,
) {

	fun couchdbDatabaseGetQueryFunction(): CouchdbDatabaseGetQueryFunction =
		CouchdbF2Function.function(config) { cmd, couchdbClient ->
			try {
				CouchdbSsmDatabaseGetQueryResult(
					item = couchdbClient.getDatabase(chainCodeDbName(cmd.channelId, cmd.chaincodeId)).asDatabase()
				)
			} catch (e: Exception) {
				CouchdbSsmDatabaseGetQueryResult(
					item = null
				)
			}

		}

	private fun DatabaseInformation.asDatabase() = Database(this.dbName)
}
