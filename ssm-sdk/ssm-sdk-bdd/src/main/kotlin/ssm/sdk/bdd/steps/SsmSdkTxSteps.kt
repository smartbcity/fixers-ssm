package ssm.sdk.bdd.steps

import SsmCommandStep
import io.cucumber.java8.En
import ssm.chaincode.dsl.model.Ssm
import ssm.chaincode.dsl.model.SsmAgent
import ssm.chaincode.dsl.model.SsmSession
import ssm.sdk.client.extention.asAgent
import ssm.sdk.sign.model.Signer
import ssm.sdk.sign.model.SignerAdmin
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

	override fun loadSignerAdmin(adminName: SignerName, filename: String?): SignerAdmin {
		return SignerAdmin.loadFromFile(adminName, filename)
	}
	override fun loadSigner(userName: SignerName, filename: String): Signer {
		return Signer.loadFromFile(userName, filename)
	}
}
