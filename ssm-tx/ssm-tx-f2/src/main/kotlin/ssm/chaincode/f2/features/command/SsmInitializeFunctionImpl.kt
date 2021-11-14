package ssm.chaincode.f2.features.command

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ssm.chaincode.dsl.model.Agent
import ssm.chaincode.dsl.model.AgentName
import ssm.chaincode.dsl.model.Ssm
import ssm.chaincode.f2.utils.SsmException
import ssm.sdk.core.SsmQueryService
import ssm.sdk.core.SsmTxService
import ssm.sdk.dsl.InvokeReturn
import ssm.tx.dsl.features.ssm.SsmInitializeCommand
import ssm.tx.dsl.features.ssm.SsmInitializedResult
import ssm.tx.dsl.features.ssm.SsmTxInitializeFunction

class SsmInitializeFunctionImpl(
	private val txService: SsmTxService,
	private val queryService: SsmQueryService,
): SsmTxInitializeFunction {

	override suspend fun invoke(msg: Flow<SsmInitializeCommand>): Flow<SsmInitializedResult> = msg.map { payload ->
		val retInitUser = initUser(payload.agent, payload.signerName)
		val retInitSsm = initSsm(payload.ssm, payload.signerName)
		val invoke = listOfNotNull(retInitUser, retInitSsm)
		SsmInitializedResult(
			results = invoke.map { it.transactionId }
		)
	}

	private suspend fun initSsm(ssm: Ssm, signerName: AgentName): InvokeReturn? {
		return createIfNotExist(ssm, { queryService.getSsm(ssm.name) }, { this.createSsm(it, signerName) })
	}

	private suspend fun initUser(user: Agent, signerName: AgentName): InvokeReturn? {
		return createIfNotExist(
			user,
			{ queryService.getAgent(user.name) },
			{ this.createUser(it, signerName)!! })
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

	private suspend fun createSsm(ssm: Ssm, signerName: AgentName): InvokeReturn {
		try {
			return txService.sendCreate(ssm, signerName)!!
		} catch (e: Exception) {
			throw SsmException(e)
		}
	}

	private suspend fun createUser(agent: Agent, signerName: AgentName): InvokeReturn? {
		try {
			return txService.sendRegisterUser(agent, signerName)
		} catch (e: Exception) {
			throw SsmException(e)
		}
	}
}
