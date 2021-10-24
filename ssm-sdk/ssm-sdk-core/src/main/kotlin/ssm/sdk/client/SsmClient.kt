package ssm.sdk.client

import com.fasterxml.jackson.core.type.TypeReference
import java.io.IOException
import ssm.sdk.client.invoke.query.AdminQuery
import ssm.sdk.client.invoke.query.AgentQuery
import ssm.sdk.client.invoke.query.BlockQuery
import ssm.sdk.client.invoke.query.LogQuery
import ssm.sdk.client.invoke.query.SessionQuery
import ssm.sdk.client.invoke.query.SsmQuery
import ssm.sdk.client.invoke.query.TransactionQuery
import ssm.chaincode.dsl.blockchain.Block
import ssm.chaincode.dsl.blockchain.BlockId
import ssm.chaincode.dsl.blockchain.Transaction
import ssm.chaincode.dsl.blockchain.TransactionId
import ssm.chaincode.dsl.model.InvokeReturn
import ssm.chaincode.dsl.model.SessionName
import ssm.chaincode.dsl.model.Ssm
import ssm.chaincode.dsl.model.SsmAgent
import ssm.chaincode.dsl.model.SsmAgentName
import ssm.chaincode.dsl.model.SsmContext
import ssm.chaincode.dsl.model.SsmName
import ssm.chaincode.dsl.model.SsmSession
import ssm.chaincode.dsl.model.SsmSessionState
import ssm.chaincode.dsl.model.SsmSessionStateLog
import ssm.sdk.client.command.CreateCmdBuilder
import ssm.sdk.client.command.PerformCmdBuilder
import ssm.sdk.client.command.RegisterCmdBuilder
import ssm.sdk.client.command.StartCmdSigner
import ssm.sdk.sign.model.Signer
import ssm.sdk.sign.model.SignerAdmin

class SsmClient(private val ssmRequester: SsmRequester) {
	@Throws(Exception::class)
	suspend fun registerUser(signer: SignerAdmin, agent: SsmAgent): InvokeReturn? {
		val cmd = RegisterCmdBuilder(agent)
		val cmdSigned = cmd.invoke(signer)
		return ssmRequester.invoke(cmdSigned)
	}

	@Throws(Exception::class)
	suspend fun create(signer: SignerAdmin, ssm: Ssm): InvokeReturn? {
		val cmd = CreateCmdBuilder(ssm)
		val cmdSigned = cmd.invoke(signer)
		return ssmRequester.invoke(cmdSigned)
	}

	@Throws(Exception::class)
	suspend fun start(signer: SignerAdmin, session: SsmSession): InvokeReturn? {
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

	suspend fun listAdmins(): List<SsmAgentName> {
		val query = AdminQuery()
		return ssmRequester.list(query, String::class.java)
	}

	suspend fun getAdmin(username: SsmAgentName): SsmAgent? {
		val query = AdminQuery()
		return ssmRequester.query(username, query, SsmAgent::class.java)
	}

	suspend fun listUsers(): List<SsmAgentName> {
		val query = AgentQuery()
		return ssmRequester.list(query, String::class.java)
	}

	suspend fun getAgent(agentName: SsmAgentName): SsmAgent? {
		val query = AgentQuery()
		return ssmRequester.query(agentName, query, SsmAgent::class.java)
	}

	suspend fun listSsm(): List<SsmName> {
		val query = SsmQuery()
		return ssmRequester.list(query, String::class.java)
	}

	suspend fun getSsm(name: SsmName): Ssm? {
		val query = SsmQuery()
		return ssmRequester.query(name, query, Ssm::class.java)
	}

	suspend fun getSession(sessionName: SessionName): SsmSessionState? {
		val query = SessionQuery()
		return ssmRequester.query(sessionName, query, SsmSessionState::class.java)
	}

	suspend fun log(sessionName: SessionName): List<SsmSessionStateLog> {
		val query = LogQuery()
		return ssmRequester.log(sessionName, query, object : TypeReference<List<SsmSessionStateLog>>() {})
	}

	suspend fun listSession(): List<String> {
		val query = SessionQuery()
		return ssmRequester.list(query, String::class.java)
	}

	suspend fun getTransaction(txId: TransactionId): Transaction? {
		val query = TransactionQuery()
		return ssmRequester.query(txId, query, Transaction::class.java)
	}

	suspend fun getBlock(blockId: BlockId): Block? {
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
