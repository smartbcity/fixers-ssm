package ssm.chaincode.f2.features.command

import f2.dsl.fnc.f2Function
import ssm.chaincode.dsl.config.SsmChaincodeConfig
import ssm.chaincode.dsl.model.Agent
import ssm.chaincode.dsl.model.Ssm
import ssm.chaincode.f2.utils.SsmException
import ssm.sdk.client.model.InvokeReturn
import ssm.sdk.core.SsmServiceFactory
import ssm.sdk.sign.SignerAdminProvider
import ssm.tx.dsl.features.ssm.SsmInitializedResult
import ssm.tx.dsl.features.ssm.SsmTxInitializeFunction

class SsmInitializeFunctionImpl(
	config: SsmChaincodeConfig,
	private val signerAdminProvider: SignerAdminProvider
) {

	private val txService = SsmServiceFactory.builder(config.url).buildTxService()
	private val queryService = SsmServiceFactory.builder(config.url).buildQueryService()

	fun ssmInitializeFunction(

	): SsmTxInitializeFunction = f2Function { cmd ->
		val retInitUser = initUser(cmd.agent)
		val retInitSsm = initSsm(cmd.ssm)
		val invoke = listOfNotNull(retInitUser, retInitSsm)
		SsmInitializedResult(
			results = invoke.map { it.transactionId }
		)
	}

	private suspend fun initSsm(ssm: Ssm): InvokeReturn? {
		return createIfNotExist(ssm, { queryService.getSsm(ssm.name) }, { this.createSsm(it) })
	}

	private suspend fun initUser(user: Agent): InvokeReturn? {
		return createIfNotExist(
			user,
			{ queryService.getAgent(user.name) },
			{ this.createUser(it)!! })
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

	private suspend fun createSsm(ssm: Ssm): InvokeReturn {
		try {
			return txService.sendCreate(signerAdminProvider.get(), ssm)!!
		} catch (e: Exception) {
			throw SsmException(e)
		}
	}

	private suspend fun createUser(agent: Agent): InvokeReturn? {
		try {
			return txService.sendRegisterUser(signerAdminProvider.get(), agent)
		} catch (e: Exception) {
			throw SsmException(e)
		}
	}
}
