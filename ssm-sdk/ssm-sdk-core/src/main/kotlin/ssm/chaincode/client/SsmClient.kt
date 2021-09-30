package ssm.chaincode.client

import java.io.IOException
import ssm.chaincode.client.invoke.command.CreateCmdBuilder
import ssm.chaincode.client.invoke.command.PerformCmdBuilder
import ssm.chaincode.client.invoke.command.RegisterCmdBuilder
import ssm.chaincode.client.invoke.command.StartCmdSigner
import ssm.chaincode.client.invoke.query.AdminQuery
import ssm.chaincode.client.invoke.query.AgentQuery
import ssm.chaincode.client.invoke.query.BlockQuery
import ssm.chaincode.client.invoke.query.LogQuery
import ssm.chaincode.client.invoke.query.SessionQuery
import ssm.chaincode.client.invoke.query.SsmQuery
import ssm.chaincode.client.invoke.query.TransactionQuery
import ssm.chaincode.dsl.model.InvokeReturn
import ssm.chaincode.dsl.model.Ssm
import ssm.chaincode.dsl.model.SsmAgent
import ssm.chaincode.dsl.model.SsmContext
import ssm.chaincode.dsl.model.SsmSession
import ssm.chaincode.dsl.model.SsmSessionState
import ssm.chaincode.dsl.model.SsmSessionStateLog
import ssm.chaincode.dsl.blockchain.Block
import ssm.chaincode.dsl.blockchain.Transaction
import ssm.sdk.sign.model.Signer

class SsmClient(private val ssmRequester: SsmRequester) {
	@Throws(Exception::class)
	suspend fun registerUser(signer: Signer, agent: SsmAgent): InvokeReturn? {
		val cmd = RegisterCmdBuilder(agent)
		val cmdSigned = cmd.invoke(signer)
		return ssmRequester.invoke(cmdSigned)
	}

	@Throws(Exception::class)
	suspend fun create(signer: Signer, ssm: Ssm): InvokeReturn? {
		val cmd = CreateCmdBuilder(ssm)
		val cmdSigned = cmd.invoke(signer)
		return ssmRequester.invoke(cmdSigned)
	}

	@Throws(Exception::class)
	suspend fun start(signer: Signer, session: SsmSession): InvokeReturn? {
		val cmd = StartCmdSigner(session)
		val cmdSigned = cmd.invoke(signer)
		return ssmRequester.invoke(cmdSigned)
	}

	@Throws(Exception::class)
	suspend fun perform(signer: Signer, action: String, context: SsmContext): InvokeReturn? {
		val cmd = PerformCmdBuilder(action, context)
		val cmdSigned = cmd.invoke(signer)
		return ssmRequester.invoke(cmdSigned)
	}

	suspend fun listAdmins(): List<String> {
		val query = AdminQuery()
		return ssmRequester.list(query, String::class.java)
	}

	suspend fun getAdmin(username: String): SsmAgent? {
		val query = AdminQuery()
		return ssmRequester.query(username, query, SsmAgent::class.java)
	}

	suspend fun listAgent(): List<String> {
		val query = AgentQuery()
		return ssmRequester.list(query, String::class.java)
	}

	suspend fun getAgent(agentName: String): SsmAgent? {
		val query = AgentQuery()
		return ssmRequester.query(agentName, query, SsmAgent::class.java)
	}

	suspend fun listSsm(): List<String> {
		val query = SsmQuery()
		return ssmRequester.list(query, String::class.java)
	}

	suspend fun getSsm(name: String): Ssm? {
		val query = SsmQuery()
		return ssmRequester.query(name, query, Ssm::class.java)
	}

	suspend fun getSession(sessionId: String): SsmSessionState? {
		val query = SessionQuery()
		return ssmRequester.query(sessionId, query, SsmSessionState::class.java)
	}

	suspend fun log(sessionId: String): List<SsmSessionStateLog> {
		val query = LogQuery()
		return ssmRequester.log(sessionId, query, SsmSessionStateLog::class.java)
	}

	suspend fun listSession(): List<String> {
		val query = SessionQuery()
		return ssmRequester.list(query, String::class.java)
	}

	suspend fun getTransaction(txId: String): Transaction? {
		val query = TransactionQuery()
		return ssmRequester.query(txId, query, Transaction::class.java)
	}

	suspend fun getBlock(blockId: Long): Block? {
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
