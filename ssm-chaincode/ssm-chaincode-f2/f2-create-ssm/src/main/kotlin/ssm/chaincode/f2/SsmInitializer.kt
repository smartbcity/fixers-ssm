package ssm.chaincode.f2

import kotlinx.coroutines.future.await
import ssm.chaincode.client.SsmClient
import ssm.chaincode.dsl.InvokeReturn
import ssm.chaincode.dsl.SsmAgent
import ssm.chaincode.dsl.Ssm
import ssm.sdk.sign.model.SignerAdmin

class SsmInitializer(
	private val ssmClient: SsmClient,
	private val signerAdmin: SignerAdmin
) {

    suspend fun init(agent: SsmAgent, ssmBase: Ssm): List<InvokeReturn> {
        val retInitUser = initUser(agent)
        val retInitSsm = initSsm(ssmBase)
        return listOfNotNull(retInitUser, retInitSsm)
    }

    suspend fun initSsm(ssmBase: Ssm): InvokeReturn? {
        return createIfNotExist(ssmBase, { this.fetchSsm(ssmBase.name) }, { this.createSsm(it) })
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
    
    private suspend fun <T> createIfNotExist(objToCreate: T,  getFnc: suspend () -> T?, create:  suspend (T) -> InvokeReturn): InvokeReturn? {
        return if(getFnc()!= null) return null
        else
            create(objToCreate)
    }

    private suspend fun createSsm(ssmBase: Ssm): InvokeReturn {
        try {
            return ssmClient.create(signerAdmin, ssmBase).await()
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }

    private suspend fun createUser(agent: SsmAgent): InvokeReturn {
        try {
            return ssmClient.registerUser(signerAdmin, agent).await()
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }
}
