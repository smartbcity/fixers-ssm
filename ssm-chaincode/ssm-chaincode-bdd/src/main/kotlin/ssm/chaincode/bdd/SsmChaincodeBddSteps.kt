package ssm.chaincode.bdd

import f2.dsl.fnc.invoke
import io.cucumber.java8.En
import kotlinx.coroutines.runBlocking
import ssm.bdd.config.SsmQueryStep
import ssm.chaincode.dsl.model.SsmSessionState
import ssm.chaincode.dsl.model.SsmSessionStateLog
import ssm.chaincode.dsl.query.SsmGetSessionLogsQuery
import ssm.chaincode.dsl.query.SsmGetSessionQuery
import ssm.chaincode.dsl.query.SsmListAdminQuery
import ssm.chaincode.dsl.query.SsmListSessionQuery
import ssm.chaincode.dsl.query.SsmListSsmQuery
import ssm.chaincode.dsl.query.SsmListUserQuery
import ssm.chaincode.f2.query.SsmChaincodeQueryFunctions

class SsmChaincodeBddSteps : SsmQueryStep(), En {
	init {
		prepareSteps()
	}

	val ssmChaincodeQueryFunctions = SsmChaincodeQueryFunctions()

	override suspend fun getSession(sessionName: String): SsmSessionState? {
		return ssmChaincodeQueryFunctions.ssmGetSessionQueryFunction(config)
			.invoke(SsmGetSessionQuery(sessionName = sessionName))
			.item
	}

	override suspend fun logSession(sessionName: String): List<SsmSessionStateLog> {
		return ssmChaincodeQueryFunctions.ssmGetSessionLogsQueryFunction(config)
			.invoke(SsmGetSessionLogsQuery(sessionName = sessionName))
			.logs
	}

	override suspend fun listSessions(): List<String> {
		return ssmChaincodeQueryFunctions.ssmListSessionQueryFunction(config)
			.invoke(SsmListSessionQuery())
			.items.toList()
	}

	override suspend fun listSsm(): List<String> {
		return ssmChaincodeQueryFunctions.ssmListSsmQueryFunction(config)
			.invoke(SsmListSsmQuery())
			.items.toList()
	}

	override suspend fun listUsers() = runBlocking {
		ssmChaincodeQueryFunctions.ssmListUserQueryFunction(config)
			.invoke(SsmListUserQuery())
			.items.toList()
	}

	override suspend fun listAdmins() = runBlocking {
		ssmChaincodeQueryFunctions.ssmListAdminQueryFunction(config)
			.invoke(SsmListAdminQuery())
			.items.toList()
	}
}
