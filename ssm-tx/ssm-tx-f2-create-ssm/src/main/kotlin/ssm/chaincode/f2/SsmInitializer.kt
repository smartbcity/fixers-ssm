package ssm.chaincode.f2

import kotlinx.coroutines.future.await
import ssm.chaincode.client.SsmClient
import ssm.chaincode.dsl.model.InvokeReturn
import ssm.chaincode.dsl.model.Ssm
import ssm.chaincode.dsl.model.SsmAgent
import ssm.chaincode.f2.commons.SsmException
import ssm.sdk.sign.model.SignerAdmin

class SsmInitializer(
	private val ssmClient: SsmClient,
	private val signerAdmin: SignerAdmin,
) {

	suspend fun init(agent: SsmAgent, ssm: Ssm): List<InvokeReturn> {
		val retInitUser = initUser(agent)
		val retInitSsm = initSsm(ssm)
		return listOfNotNull(retInitUser, retInitSsm)
	}

	suspend fun initSsm(ssm: Ssm): InvokeReturn? {
		return createIfNotExist(ssm, { this.fetchSsm(ssm.name) }, { this.createSsm(it) })
	}

	suspend fun initUser(user: SsmAgent): InvokeReturn? {
		return createIfNotExist(user, { this.fetchUser(user.name) }, { this.createUser(it)!! })
	}

	private suspend fun fetchSsm(name: String): Ssm? {
		return ssmClient.getSsm(name)
	}

	private suspend fun fetchUser(name: String): SsmAgent? {
		return ssmClient.getAgent(name)
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
			return ssmClient.create(signerAdmin, ssm)!!
		} catch (e: Exception) {
			throw SsmException(e)
		}
	}

	private suspend fun createUser(agent: SsmAgent): InvokeReturn? {
		try {
			return ssmClient.registerUser(signerAdmin, agent)
		} catch (e: Exception) {
			throw SsmException(e)
		}
	}
}
