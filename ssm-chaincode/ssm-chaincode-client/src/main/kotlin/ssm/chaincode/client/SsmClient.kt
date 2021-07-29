package ssm.chaincode.client

import ssm.chaincode.client.invoke.command.CreateCommandSigner
import ssm.chaincode.client.invoke.command.PerformCommandSigner
import ssm.chaincode.client.invoke.command.RegisterCommandSigner
import ssm.chaincode.client.invoke.command.StartCommandSigner
import ssm.chaincode.client.invoke.query.*
import ssm.chaincode.dsl.*
import ssm.chaincode.dsl.blockchain.Block
import ssm.chaincode.dsl.blockchain.Transaction
import ssm.sdk.sign.model.Signer
import java.io.IOException
import java.util.concurrent.CompletableFuture

class SsmClient(private val ssmRequester: SsmRequester) {
    @Throws(Exception::class)
    fun registerUser(signer: Signer?, agent: SsmAgent?): CompletableFuture<InvokeReturn> {
        val cmd = RegisterCommandSigner(signer, agent)
        return ssmRequester.invoke(cmd)
    }

    @Throws(Exception::class)
    fun create(signer: Signer?, ssm: Ssm?): CompletableFuture<InvokeReturn> {
        val cmd = CreateCommandSigner(signer, ssm)
        return ssmRequester.invoke(cmd)
    }

    @Throws(Exception::class)
    fun start(signer: Signer?, session: SsmSession?): CompletableFuture<InvokeReturn> {
        val cmd = StartCommandSigner(signer, session)
        return ssmRequester.invoke(cmd)
    }

    @Throws(Exception::class)
    fun perform(signer: Signer?, action: String, context: SsmContext?): CompletableFuture<InvokeReturn> {
        val cmd = PerformCommandSigner(signer, action, context)
        return ssmRequester.invoke(cmd)
    }

    fun listAdmins(): CompletableFuture<List<String>> {
        val query = AdminQuery()
        return ssmRequester.list(query, String::class.java)
    }

    fun getAdmin(username: String): CompletableFuture<SsmAgent> {
        val query = AdminQuery()
        return ssmRequester.query(username, query, SsmAgent::class.java)
    }

    fun listAgent(): CompletableFuture<List<String>> {
        val query = AgentQuery()
        return ssmRequester.list(query, String::class.java)
    }

    fun getAgent(agentName: String): CompletableFuture<SsmAgent> {
        val query = AgentQuery()
        return ssmRequester.query(agentName, query, SsmAgent::class.java)
    }

    fun listSsm(): CompletableFuture<List<String>> {
        val query = SsmQuery()
        return ssmRequester.list(query, String::class.java)
    }

    fun getSsm(name: String): CompletableFuture<Ssm> {
        val query = SsmQuery()
        return ssmRequester.query(name, query, Ssm::class.java)
    }

    fun getSession(sessionId: String): CompletableFuture<SsmSessionStateBase> {
        val query = SessionQuery()
        return ssmRequester.query(sessionId, query, SsmSessionStateBase::class.java)
    }

    fun log(sessionId: String): CompletableFuture<List<SsmSessionStateLog>> {
        val query = LogQuery()
        return ssmRequester.log(sessionId, query, SsmSessionStateLog::class.java)
    }

    fun listSession(): CompletableFuture<List<String>> {
        val query = SessionQuery()
        return ssmRequester.list(query, String::class.java)
    }

    fun getTransaction(txId: String): CompletableFuture<Transaction> {
        val query = TransactionQuery()
        return ssmRequester.query(txId, query, Transaction::class.java)
    }

    fun getBlock(blockId: Long): CompletableFuture<Block> {
        val query = BlockQuery()
        return ssmRequester.query(blockId.toString(), query, Block::class.java)
    }

    companion object {
        @Throws(IOException::class)
        fun fromConfigFile(filename: String): SsmClient {
            return SsmClientBuilder.builder(filename).build()
        }

        fun fromConfig(config: SsmClientConfig): SsmClient {
            return SsmClientBuilder.builder(config).build()
        }
    }
}