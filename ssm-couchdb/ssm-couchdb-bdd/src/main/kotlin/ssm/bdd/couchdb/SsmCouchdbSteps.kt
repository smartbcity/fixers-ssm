package ssm.bdd.couchdb

import f2.dsl.fnc.invoke
import io.cucumber.java8.En
import ssm.bdd.config.SsmQueryStep
import ssm.chaincode.dsl.model.SessionName
import ssm.chaincode.dsl.model.SsmName
import ssm.chaincode.dsl.model.SsmSessionStateDTO
import ssm.chaincode.dsl.model.SsmSessionStateLog
import ssm.chaincode.dsl.model.uri.ChaincodeUri
import ssm.chaincode.dsl.model.uri.SsmUri
import ssm.chaincode.dsl.model.uri.burstChaincode
import ssm.couchdb.dsl.config.SsmCouchdbConfig
import ssm.couchdb.dsl.query.CouchdbAdminListQuery
import ssm.couchdb.dsl.query.CouchdbSsmListQuery
import ssm.couchdb.dsl.query.CouchdbSsmSessionStateGetQuery
import ssm.couchdb.dsl.query.CouchdbSsmSessionStateListQuery
import ssm.couchdb.dsl.query.CouchdbUserListQuery
import ssm.couchdb.f2.CouchdbSsmQueriesFunctionImpl

class SsmCouchdbSteps : SsmQueryStep(), En {
	val couchdbSsmQueriesFunctions = CouchdbSsmQueriesFunctionImpl()

	lateinit var ssmUri: SsmUri
	lateinit var chaincodeUri: ChaincodeUri
	val configCouchDb: SsmCouchdbConfig = TestConfig.dbConfig

	init {
		prepareSteps()
	}

	override suspend fun getSession(sessionName: String): SsmSessionStateDTO {
		return couchdbSsmQueriesFunctions.couchdbSsmSessionStateGetQueryFunction(configCouchDb)
			.invoke(
				CouchdbSsmSessionStateGetQuery(
					ssmUri = ssmUri,
					sessionName = sessionName
				)
			).item
	}

	override suspend fun logSession(sessionName: String) = emptyList<SsmSessionStateLog>()
//		runBlocking {
//		couchDbSsmQueriesFunctions.ssmGetSessionLogsQueryFunction(bag.config)
//			.invoke(SsmGetSessionLogsQuery(sessionName = sessionName))
//			.logs
//	}

	override suspend fun listSessions(): List<SessionName> {
		return couchdbSsmQueriesFunctions.couchdbSsmSessionStateListQueryFunction(configCouchDb)
			.invoke(CouchdbSsmSessionStateListQuery(chaincodeUri = chaincodeUri)).page.list.map { it.session }
	}

	override suspend fun listSsm(): List<SsmName> {
		val uri = chaincodeUri.burstChaincode()!!
		return couchdbSsmQueriesFunctions.couchdbSsmListQueryFunction(configCouchDb)
			.invoke(
				CouchdbSsmListQuery(
					pagination = null,
					channelId = uri.channelId,
					chaincodeId = uri.chaincodeId
				)
			).page.list.map { it.name }
	}

	override suspend fun listUsers(): List<String> {
		return couchdbSsmQueriesFunctions.couchdbUserListQueryFunction(configCouchDb)
			.invoke(CouchdbUserListQuery(chaincodeUri))
			.items.map { it.name }
	}

	override suspend fun listAdmins(): List<String> {
		return couchdbSsmQueriesFunctions.couchdbAdminListQueryFunction(configCouchDb)
			.invoke(CouchdbAdminListQuery(chaincodeUri))
			.items.map { it.name }
	}
}
