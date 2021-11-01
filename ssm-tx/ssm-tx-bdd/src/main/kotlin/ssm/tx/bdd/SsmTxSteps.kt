package ssm.tx.bdd

import ssm.bdd.config.SsmCommandStep
import f2.dsl.fnc.invoke
import io.cucumber.java8.En
import ssm.chaincode.dsl.config.SsmChaincodeConfig
import ssm.chaincode.dsl.model.Ssm
import ssm.chaincode.dsl.model.Agent
import ssm.chaincode.dsl.model.SsmSession
import ssm.chaincode.f2.SsmTxAdminServiceImpl
import ssm.sdk.sign.SignerAdminProvider
import ssm.sdk.sign.model.Signer
import ssm.sdk.sign.model.SignerAdmin
import ssm.sdk.sign.model.SignerName
import ssm.tx.dsl.features.ssm.SsmCreateCommand
import ssm.tx.dsl.features.ssm.SsmSessionStartCommand
import ssm.tx.dsl.features.user.SsmUserRegisterCommand


class SsmTxSteps : SsmCommandStep(), En {

	private var ssmTxAdminServiceImpl: SsmTxAdminServiceImpl

	init {
		prepareSteps()

		val signerAdminProvider = object : SignerAdminProvider {
			override fun get(): SignerAdmin {
				return bag.adminSigner
			}
		}
		ssmTxAdminServiceImpl = SsmTxAdminServiceImpl(signerAdminProvider = signerAdminProvider)

	}

	override suspend fun startSession(session: SsmSession) {
		ssmTxAdminServiceImpl.ssmTxSessionStartFunction(SsmChaincodeConfig(
			bag.config.baseUrl
		)).invoke(SsmSessionStartCommand(
			session
		))
	}

	override suspend fun createSsm(ssm: Ssm) {
		ssmTxAdminServiceImpl.ssmTxCreateFunction(SsmChaincodeConfig(
			bag.config.baseUrl
		)).invoke(SsmCreateCommand(
			ssm = ssm
		))
	}

	override suspend fun registerUser(ssmAgent: Agent) {
		ssmTxAdminServiceImpl.ssmTxUserRegisterFunction(SsmChaincodeConfig(
			bag.config.baseUrl
		)).invoke(SsmUserRegisterCommand(
				ssmAgent
		))
	}

	override suspend fun loadSigner(userName: SignerName, filename: String): Signer {
		return Signer.loadFromFile(userName, filename)
	}
}
