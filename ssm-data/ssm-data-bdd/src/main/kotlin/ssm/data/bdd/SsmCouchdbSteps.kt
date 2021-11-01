package ssm.data.bdd

import f2.dsl.fnc.invoke
import io.cucumber.java8.En
import ssm.api.DataSsmQueryFunctionImpl
import ssm.bdd.config.SsmQueryStep
import ssm.chaincode.dsl.model.SessionName
import ssm.chaincode.dsl.model.SsmName
import ssm.chaincode.dsl.model.SsmSessionStateDTO
import ssm.chaincode.dsl.model.SsmSessionStateLog
import ssm.couchdb.dsl.query.CouchdbAdminListQuery
import ssm.couchdb.dsl.query.CouchdbUserListQuery
import ssm.couchdb.f2.CouchdbSsmQueriesFunctionImpl
import ssm.data.dsl.config.SsmDataConfig
import ssm.data.dsl.features.query.DataSsmListQuery
import ssm.data.dsl.features.query.DataSsmSessionGetQuery
import ssm.data.dsl.features.query.DataSsmSessionListQuery

class SsmCouchdbSteps : SsmQueryStep(), En {
	val dataSsmQueryFunctionImpl = DataSsmQueryFunctionImpl()
	val couchdbSsmQueriesFunctionImpl = CouchdbSsmQueriesFunctionImpl()
	val ssmDataConfig: SsmDataConfig = TestConfig.local

	init {
		prepareSteps()
	}

	override suspend fun getSession(sessionName: String): SsmSessionStateDTO {
		return dataSsmQueryFunctionImpl.dataSsmSessionGetQueryFunction(ssmDataConfig)
			.invoke(
				DataSsmSessionGetQuery(
					sessionName = sessionName,
					ssm = bag.ssmUri,
				)
			).item!!.state.details
	}

	override suspend fun logSession(sessionName: String): List<SsmSessionStateLog> {
		return bag.client.log(sessionName)
	}

	override suspend fun listSessions(): List<SessionName> {
		couchdbSsmQueriesFunctionImpl.couchdbAdminListQueryFunction(config = ssmDataConfig.couchdb).invoke(
			CouchdbAdminListQuery(
				chaincodeUri = bag.chaincodeUri
			))
		return dataSsmQueryFunctionImpl.dataSsmSessionListQueryFunction(ssmDataConfig)
			.invoke(DataSsmSessionListQuery(
				ssm = bag.ssmUri
			)).items.map { it.state.details.session }
	}

	override suspend fun listSsm(): List<SsmName> {
		return dataSsmQueryFunctionImpl.dataSsmListQueryFunction(ssmDataConfig)
			.invoke(
				DataSsmListQuery(
					chaincodes = listOf(bag.chaincodeUri)
				)
			).items.map { it.ssm.name }
	}

	override suspend fun listUsers(): List<String> {
		return couchdbSsmQueriesFunctionImpl.couchdbUserListQueryFunction(ssmDataConfig.couchdb)
			.invoke(CouchdbUserListQuery(chaincodeUri = bag.chaincodeUri))
			.items.map { it.name }
	}

	override suspend fun listAdmins(): List<String> {
		return couchdbSsmQueriesFunctionImpl.couchdbAdminListQueryFunction(ssmDataConfig.couchdb)
			.invoke(CouchdbAdminListQuery(chaincodeUri = bag.chaincodeUri))
			.items.map { it.name }
	}
}
