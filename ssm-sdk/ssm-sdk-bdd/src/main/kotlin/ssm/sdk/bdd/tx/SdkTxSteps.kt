package ssm.sdk.bdd.tx

import io.cucumber.java8.En
import ssm.bdd.config.SsmCommandStep
import ssm.chaincode.dsl.model.Agent
import ssm.chaincode.dsl.model.Ssm
import ssm.chaincode.dsl.model.SsmSession
import ssm.sdk.sign.model.SignerName
import ssm.sdk.sign.model.SignerUser

class SdkTxSteps : SsmCommandStep(), En {
	companion object {
		const val GLUE = "ssm.sdk.bdd.tx"
	}

	init {
		prepareSteps()
	}

	override suspend fun startSession(session: SsmSession) {
		bag.clientTx.sendStart(bag.adminSigner, session)
	}

	override suspend fun createSsm(ssm: Ssm) {
		bag.clientTx.sendCreate(bag.adminSigner, ssm)
	}

	override suspend fun registerUser(ssmAgent: Agent) {
		bag.clientTx.sendRegisterUser(bag.adminSigner, ssmAgent)
	}

	override suspend fun loadSigner(userName: SignerName, filename: String): SignerUser {
		return SignerUser.loadFromFile(userName, filename)
	}
}
