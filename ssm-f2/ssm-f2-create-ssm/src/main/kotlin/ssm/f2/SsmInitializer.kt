package ssm.f2

import kotlinx.coroutines.future.await
import ssm.client.SsmClient
import ssm.client.sign.model.SignerAdmin
import ssm.dsl.InvokeReturn
import ssm.dsl.SsmBase
import ssm.dsl.SsmAgentBase

class SsmInitializer(
        private val ssmClient: SsmClient,
        private val signerAdmin: SignerAdmin
) {

    suspend fun init(agent: SsmAgentBase, ssmBase: SsmBase): List<InvokeReturn> {
        val retInitUser = initUser(agent)
        val retInitSsm = initSsm(ssmBase)
        return listOfNotNull(retInitUser, retInitSsm)
    }

    suspend fun initSsm(ssmBase: SsmBase): InvokeReturn? {
        return createIfNotExist(ssmBase, { this.fetchSsm(ssmBase.name) }, { this.createSsm(it) })
    }

    suspend fun initUser(user: SsmAgentBase): InvokeReturn? {
        return createIfNotExist(user, { this.fetchUser(user.name) }, { this.createUser(it) })
    }

    private suspend fun fetchSsm(name: String): SsmBase? {
        return ssmClient.getSsm(name).await().orElse(null)
    }

    private suspend fun fetchUser(name: String): SsmAgentBase? {
        return ssmClient.getAgent(name).await().orElse(null)
    }
    
    private suspend fun <T> createIfNotExist(objToCreate: T,  getFnc: suspend () -> T?, create:  suspend (T) -> InvokeReturn): InvokeReturn? {
        return if(getFnc()!= null) return null
        else
            create(objToCreate)
    }

    private suspend fun createSsm(ssmBase: SsmBase): InvokeReturn {
        try {
            return ssmClient.create(signerAdmin, ssmBase).await()
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }

    private suspend fun createUser(agent: SsmAgentBase): InvokeReturn {
        try {
            return ssmClient.registerUser(signerAdmin, agent).await()
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }
}
