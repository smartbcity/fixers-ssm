package ssm.sdk.bdd.query

import io.cucumber.java8.En
import ssm.bdd.config.SsmQueryStep
import ssm.chaincode.dsl.model.SessionName
import ssm.chaincode.dsl.model.SsmName
import ssm.chaincode.dsl.model.SsmSessionState
import ssm.chaincode.dsl.model.SsmSessionStateLog
import ssm.chaincode.dsl.model.uri.SsmUri

class SdkQuerySteps : SsmQueryStep(), En {
	companion object {
		const val GLUE = "ssm.sdk.bdd.query"
	}

	init {
		prepareSteps()
	}

	override suspend fun getSession(ssmName: SsmUri, sessionName: SessionName): SsmSessionState? {
		return bag.clientQuery.getSession(sessionName)
	}

	override suspend fun logSession(ssmName: SsmUri, sessionName: SessionName): List<SsmSessionStateLog> {
		return bag.clientQuery.log(sessionName)
	}

	override suspend fun listSessions(ssmUri: SsmUri): List<SessionName> {
		return bag.clientQuery.listSession()
	}

	override suspend fun listSsm(): List<SsmName> {
		return bag.clientQuery.listSsm()
	}

	override suspend fun listUsers(): List<String> {
		return bag.clientQuery.listUsers()
	}

	override suspend fun listAdmins(): List<String> {
		return bag.clientQuery.listAdmins()
	}
}
