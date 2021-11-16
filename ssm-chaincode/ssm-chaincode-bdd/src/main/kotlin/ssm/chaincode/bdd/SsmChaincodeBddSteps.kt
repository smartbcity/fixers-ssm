package ssm.chaincode.bdd

import f2.dsl.fnc.invoke
import io.cucumber.java8.En
import io.cucumber.java8.Scenario
import kotlinx.coroutines.runBlocking
import ssm.bdd.config.SsmQueryStep
import ssm.chaincode.dsl.model.SsmName
import ssm.chaincode.dsl.model.SsmSessionState
import ssm.chaincode.dsl.model.SsmSessionStateLog
import ssm.chaincode.dsl.query.SsmGetSessionLogsQuery
import ssm.chaincode.dsl.query.SsmGetSessionQuery
import ssm.chaincode.dsl.query.SsmListAdminQuery
import ssm.chaincode.dsl.query.SsmListSessionQuery
import ssm.chaincode.dsl.query.SsmListSsmQuery
import ssm.chaincode.dsl.query.SsmListUserQuery
import ssm.chaincode.f2.ChaincodeSsmQueriesImpl

class SsmChaincodeBddSteps : SsmQueryStep(), En {
	companion object {
		const val GLUE = "ssm.chaincode.bdd"
	}
	init {
		prepareSteps()
		Before { scenario: Scenario ->
			ssmChaincodeQueryFunctions = ChaincodeSsmQueriesImpl(config)
		}

	}
	lateinit var ssmChaincodeQueryFunctions: ChaincodeSsmQueriesImpl

	override suspend fun getSession(ssmName: SsmName, sessionName: String): SsmSessionState? {
		return ssmChaincodeQueryFunctions.ssmGetSessionQueryFunction()
			.invoke(SsmGetSessionQuery(sessionName = sessionName))
			.item
	}

	override suspend fun logSession(sessionName: String): List<SsmSessionStateLog> {
		return ssmChaincodeQueryFunctions.ssmGetSessionLogsQueryFunction()
			.invoke(SsmGetSessionLogsQuery(sessionName = sessionName))
			.logs
	}

	override suspend fun listSessions(): List<String> {
		return ssmChaincodeQueryFunctions.ssmListSessionQueryFunction()
			.invoke(SsmListSessionQuery())
			.items.toList()
	}

	override suspend fun listSsm(): List<String> {
		return ssmChaincodeQueryFunctions.ssmListSsmQueryFunction()
			.invoke(SsmListSsmQuery())
			.items.toList()
	}

	override suspend fun listUsers() = runBlocking {
		ssmChaincodeQueryFunctions.ssmListUserQueryFunction()
			.invoke(SsmListUserQuery())
			.items.toList()
	}

	override suspend fun listAdmins() = runBlocking {
		ssmChaincodeQueryFunctions.ssmListAdminQueryFunction()
			.invoke(SsmListAdminQuery())
			.items.toList()
	}
}
