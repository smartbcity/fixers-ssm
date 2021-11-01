package ssm.chaincode.f2.features.command

import ssm.chaincode.dsl.config.SsmChaincodeConfig
import ssm.chaincode.dsl.model.Agent
import ssm.chaincode.dsl.model.Ssm
import ssm.chaincode.f2.utils.SsmException
import ssm.chaincode.f2.utils.ssmF2Function
import ssm.sdk.client.SsmClient
import ssm.sdk.client.model.InvokeReturn
import ssm.sdk.sign.SignerAdminProvider
import ssm.sdk.sign.model.SignerAdmin
import ssm.tx.dsl.features.ssm.SsmInitializedResult
import ssm.tx.dsl.features.ssm.SsmTxInitializeFunction

class SsmInitializeFunctionImpl {

	fun ssmInitializeFunction(
		config: SsmChaincodeConfig, signerAdminProvider: SignerAdminProvider
	): SsmTxInitializeFunction = ssmF2Function(config) { cmd, ssmClient ->
		val signer = signerAdminProvider.get()
		val retInitUser = initUser(cmd.agent, ssmClient, signer)
		val retInitSsm = initSsm(cmd.ssm, ssmClient, signer)
		val invoke = listOfNotNull(retInitUser, retInitSsm)
		SsmInitializedResult(
			results = invoke.map { it.transactionId }
		)
	}

	private suspend fun initSsm(ssm: Ssm, ssmClient: SsmClient, signerAdmin: SignerAdmin): InvokeReturn? {
		return createIfNotExist(ssm, { ssmClient.getSsm(ssm.name) }, { this.createSsm(it, ssmClient, signerAdmin) })
	}

	private suspend fun initUser(user: Agent, ssmClient: SsmClient, signerAdmin: SignerAdmin): InvokeReturn? {
		return createIfNotExist(
			user,
			{ ssmClient.getAgent(user.name) },
			{ this.createUser(it, ssmClient, signerAdmin)!! })
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

	private suspend fun createUser(agent: Agent, ssmClient: SsmClient, signerAdmin: SignerAdmin): InvokeReturn? {
		try {
			return ssmClient.registerUser(signerAdmin, agent)
		} catch (e: Exception) {
			throw SsmException(e)
		}
	}
}
