package ssm.chaincode.f2

import ssm.chaincode.dsl.config.SsmChaincodeConfig
import ssm.chaincode.dsl.model.InvokeReturn
import ssm.chaincode.dsl.model.Ssm
import ssm.chaincode.dsl.model.SsmAgent
import ssm.chaincode.f2.commons.SsmException
import ssm.chaincode.f2.commons.ssmF2Function
import ssm.sdk.client.SsmClient
import ssm.sdk.sign.SignerAdminProvider
import ssm.sdk.sign.model.SignerAdmin
import ssm.tx.dsl.features.ssm.SsmInitializeFunction
import ssm.tx.dsl.features.ssm.SsmInitializedResult

class SsmInitializeFunctionImpl {

	fun ssmInitializeFunction(
		config: SsmChaincodeConfig, signerAdminProvider: SignerAdminProvider
	): SsmInitializeFunction = ssmF2Function(config) { cmd, ssmClient ->
		val signer = signerAdminProvider.get()
		val retInitUser = initUser(cmd.agent, ssmClient, signer)
		val retInitSsm = initSsm(cmd.ssm, ssmClient, signer)
		val invoke = listOfNotNull(retInitUser, retInitSsm)
		SsmInitializedResult(invoke)
	}

	private suspend fun initSsm(ssm: Ssm, ssmClient: SsmClient, signerAdmin: SignerAdmin): InvokeReturn? {
		return createIfNotExist(ssm, { ssmClient.getSsm(ssm.name) }, { this.createSsm(it, ssmClient, signerAdmin) })
	}

	private suspend fun initUser(user: SsmAgent, ssmClient: SsmClient, signerAdmin: SignerAdmin): InvokeReturn? {
		return createIfNotExist(user, { ssmClient.getAgent(user.name) }, { this.createUser(it, ssmClient, signerAdmin)!! })
	}

	private suspend fun <T> createIfNotExist(
		objToCreate: T,
		getFnc: suspend () -> T?,
		create: suspend (T) -> InvokeReturn,
	): InvokeReturn? {
		return if (getFnc() != null) {
			null
		} else {
			create(objToCreate)
		}
	}

	private suspend fun createSsm(ssm: Ssm, ssmClient: SsmClient, signerAdmin: SignerAdmin): InvokeReturn {
		try {
			return ssmClient.create(signerAdmin, ssm)!!
		} catch (e: Exception) {
			throw SsmException(e)
		}
	}

	private suspend fun createUser(agent: SsmAgent, ssmClient: SsmClient, signerAdmin: SignerAdmin): InvokeReturn? {
		try {
			return ssmClient.registerUser(signerAdmin, agent)
		} catch (e: Exception) {
			throw SsmException(e)
		}
	}
}
