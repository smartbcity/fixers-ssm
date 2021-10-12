package ssm.chaincode.f2

import kotlinx.coroutines.future.await
import ssm.chaincode.client.SsmClient
import ssm.chaincode.dsl.InvokeReturn
import ssm.chaincode.dsl.Ssm
import ssm.chaincode.dsl.SsmAgent
import ssm.chaincode.f2.commons.SsmException
import ssm.sdk.sign.model.SignerAdmin

class SsmInitializer(
	private val ssmClient: SsmClient,
	private val signerAdmin: SignerAdmin,
) {

	suspend fun init(agent: SsmAgent?, ssm: Ssm): List<InvokeReturn> {
		val retInitUser = agent?.let { initUser(it) }
		val retInitSsm = initSsm(ssm)
		return listOfNotNull(retInitUser, retInitSsm)
	}

	suspend fun initSsm(ssm: Ssm): InvokeReturn? {
		return createIfNotExist(ssm, { this.fetchSsm(ssm.name) }, { this.createSsm(it) })
	}

	suspend fun initUser(user: SsmAgent): InvokeReturn? {
		return createIfNotExist(user, { this.fetchUser(user.name) }, { this.createUser(it) })
	}

	private suspend fun fetchSsm(name: String): Ssm? {
		return ssmClient.getSsm(name).await()
	}

	private suspend fun fetchUser(name: String): SsmAgent? {
		return ssmClient.getAgent(name).await()
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
			return ssmClient.create(signerAdmin, ssm).await()
		} catch (e: Exception) {
			throw SsmException(e)
		}
	}

	private suspend fun createUser(agent: SsmAgent): InvokeReturn {
		try {
			return ssmClient.registerUser(signerAdmin, agent).await()
		} catch (e: Exception) {
			throw SsmException(e)
		}
	}
}
