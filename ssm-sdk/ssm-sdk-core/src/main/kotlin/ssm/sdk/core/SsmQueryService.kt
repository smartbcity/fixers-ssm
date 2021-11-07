package ssm.sdk.core

import com.fasterxml.jackson.core.type.TypeReference
import ssm.chaincode.dsl.blockchain.Block
import ssm.chaincode.dsl.blockchain.BlockId
import ssm.chaincode.dsl.blockchain.Transaction
import ssm.chaincode.dsl.blockchain.TransactionId
import ssm.chaincode.dsl.model.Agent
import ssm.chaincode.dsl.model.AgentName
import ssm.chaincode.dsl.model.SessionName
import ssm.chaincode.dsl.model.Ssm
import ssm.chaincode.dsl.model.SsmName
import ssm.chaincode.dsl.model.SsmSessionState
import ssm.chaincode.dsl.model.SsmSessionStateLog
import ssm.sdk.client.SsmRequester
import ssm.sdk.client.invoke.query.AdminQuery
import ssm.sdk.client.invoke.query.AgentQuery
import ssm.sdk.client.invoke.query.BlockQuery
import ssm.sdk.client.invoke.query.LogQuery
import ssm.sdk.client.invoke.query.SessionQuery
import ssm.sdk.client.invoke.query.SsmQuery
import ssm.sdk.client.invoke.query.TransactionQuery

class SsmQueryService(private val ssmRequester: SsmRequester) {
	suspend fun listAdmins(): List<AgentName> {
		val query = AdminQuery()
		return ssmRequester.list(query, String::class.java)
	}

	suspend fun getAdmin(username: AgentName): Agent? {
		val query = AdminQuery()
		return ssmRequester.query(username, query, Agent::class.java)
	}

	suspend fun listUsers(): List<AgentName> {
		val query = AgentQuery()
		return ssmRequester.list(query, String::class.java)
	}

	suspend fun getAgent(agentName: AgentName): Agent? {
		val query = AgentQuery()
		return ssmRequester.query(agentName, query, Agent::class.java)
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
}
