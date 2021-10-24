package ssm.sdk.bdd.steps

import SsmQueryStep
import io.cucumber.java8.En
import ssm.chaincode.dsl.model.SessionName
import ssm.chaincode.dsl.model.SsmName
import ssm.chaincode.dsl.model.SsmSessionState
import ssm.chaincode.dsl.model.SsmSessionStateLog

class SsmSdkQuerySteps : SsmQueryStep(), En {
	init {
		prepareSteps()
	}

	override suspend fun getSession(sessionName: SessionName): SsmSessionState? {
		return bag.client.getSession(sessionName)
	}

	override suspend fun logSession(sessionName: SessionName): List<SsmSessionStateLog> {
		return bag.client.log(sessionName)
	}

	override suspend fun listSessions(): List<SessionName> {
		return bag.client.listSession()
	}

	override suspend fun listSsm(): List<SsmName> {
		return bag.client.listSsm()
	}

	override suspend fun listUsers(): List<String> {
		return bag.client.listUsers()
	}

	override suspend fun listAdmins(): List<String> {
		return bag.client.listAdmins()
	}
}
