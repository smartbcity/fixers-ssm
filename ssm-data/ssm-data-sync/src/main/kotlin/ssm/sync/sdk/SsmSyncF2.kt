package ssm.sync.sdk

import f2.dsl.fnc.F2Function
import f2.dsl.fnc.f2Function
import f2.dsl.fnc.invoke
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import ssm.chaincode.dsl.model.uri.ChaincodeUriBurstDTO
import ssm.chaincode.dsl.model.uri.burstChaincode
import ssm.chaincode.dsl.query.SsmGetSessionLogsQuery
import ssm.chaincode.dsl.query.SsmGetSessionLogsQueryFunction
import ssm.chaincode.dsl.query.SsmGetSessionLogsQueryResult
import ssm.chaincode.f2.query.SsmGetSessionLogsQueryFunctionImpl
import ssm.couchdb.dsl.model.DatabaseChangesDTO
import ssm.couchdb.dsl.model.DocType
import ssm.couchdb.dsl.query.CouchdbDatabaseGetChangesQueryFunction
import ssm.couchdb.dsl.query.CouchdbDatabaseGetChangesQuery
import ssm.couchdb.f2.query.CouchDbDatabaseGetChangesQueryFunctionImpl
import ssm.data.dsl.config.SsmDataConfig

class SsmSyncF2(
	private val config: SsmDataConfig,
	private val chaincodeQueryFunctions: SsmGetSessionLogsQueryFunction =
		SsmGetSessionLogsQueryFunctionImpl().ssmGetSessionLogsQueryFunction(config.chaincode),


	private val couchDbDatabaseGetChangesQueryFunction: CouchdbDatabaseGetChangesQueryFunction =
		CouchDbDatabaseGetChangesQueryFunctionImpl(config.couchdb).couchDbDatabaseGetChangesQueryFunction()
) {

	fun syncSsmCommandFunction(): F2Function<SyncSsmCommand, Flow<SsmGetSessionLogsQueryResult>> = f2Function { query ->
		query.chaincode
			.burstChaincode()
			?.queryCouchdbDatabaseGetChanges(query)
			?.filterNotNull()
			?: emptyList<SsmGetSessionLogsQueryResult>().asFlow()
	}

	private suspend fun ChaincodeUriBurstDTO.queryCouchdbDatabaseGetChanges(
		cmd: SyncSsmCommand,
	) = let { uri ->
		CouchdbDatabaseGetChangesQuery(
			chaincodeId = uri.chaincodeId,
			channelId = uri.channelId,
			docType = DocType.State,
			lastEventId = cmd.lastEventId
		)
	}.let {
		couchDbDatabaseGetChangesQueryFunction.invoke(it).items
			.asFlow()
			.applySsmSessionChanges()
	}

	private fun Flow<DatabaseChangesDTO>.applySsmSessionChanges() = map { changes ->
		try {
			chaincodeQueryFunctions.invoke(
				SsmGetSessionLogsQuery(
					sessionName = changes.objectId,
				)
			).also {
				println(it.logs)
			}
		} catch (e: Exception) {
			null
		}
	}
}
