package ssm.sdk.bdd.tx

import io.cucumber.java8.En
import ssm.bdd.config.SsmCommandStep
import ssm.chaincode.dsl.model.Ssm
import ssm.chaincode.dsl.model.SsmAgent
import ssm.chaincode.dsl.model.SsmSession
import ssm.sdk.sign.model.Signer
import ssm.sdk.sign.model.SignerName

class SsmSdkTxSteps : SsmCommandStep(), En {

	init {
		prepareSteps()
	}

	override suspend fun startSession(session: SsmSession) {
		bag.client.start(bag.adminSigner, session)
	}

	override suspend fun createSsm(ssm: Ssm) {
		bag.client.create(bag.adminSigner, ssm)
	}

	override suspend fun registerUser(ssmAgent: SsmAgent) {
		bag.client.registerUser(bag.adminSigner, ssmAgent)
	}

	override suspend fun loadSigner(userName: SignerName, filename: String): Signer {
		return Signer.loadFromFile(userName, filename)
	}
}
