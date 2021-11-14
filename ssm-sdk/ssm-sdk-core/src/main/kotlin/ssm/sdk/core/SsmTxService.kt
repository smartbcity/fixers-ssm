package ssm.sdk.core

import ssm.chaincode.dsl.model.Agent
import ssm.chaincode.dsl.model.AgentName
import ssm.chaincode.dsl.model.Ssm
import ssm.chaincode.dsl.model.SsmContext
import ssm.chaincode.dsl.model.SsmSession
import ssm.sdk.core.ktor.SsmRequester
import ssm.sdk.core.invoke.command.CreateCmd
import ssm.sdk.core.invoke.command.PerformCmd
import ssm.sdk.core.invoke.command.RegisterCmd
import ssm.sdk.core.invoke.command.StartCmd
import ssm.sdk.dsl.InvokeReturn
import ssm.sdk.dsl.SsmCmd
import ssm.sdk.dsl.SsmCmdSigned
import ssm.sdk.sign.SsmCmdSigner

class SsmTxService(
	private val ssmRequester: SsmRequester,
	private val ssmCmdSigner: SsmCmdSigner
) {

	fun registerUser(agent: Agent): SsmCmd {
		val cmd = RegisterCmd(agent)
		return cmd.commandToSign()
	}

	fun create(ssm: Ssm): SsmCmd {
		val cmd = CreateCmd(ssm)
		return cmd.commandToSign()
	}

	fun start(session: SsmSession): SsmCmd {
		val cmd = StartCmd(session)
		return cmd.commandToSign()
	}

	fun perform(action: String, context: SsmContext): SsmCmd {
		val cmd = PerformCmd(action, context)
		return cmd.commandToSign()
	}


	suspend fun sendRegisterUser(agent: Agent, signerName: AgentName): InvokeReturn? {
		return signAndSend(signerName) {
			registerUser(agent)
		}
	}

	suspend fun sendCreate(ssm: Ssm, signerName: AgentName): InvokeReturn? {
		return signAndSend(signerName) {
			create(ssm)
		}
	}

	suspend fun sendStart(session: SsmSession, signerName: AgentName): InvokeReturn? {
		return signAndSend(signerName) {
			start(session)
		}
	}

	suspend fun sendPerform(action: String, context: SsmContext, signerName: AgentName): InvokeReturn? {
		return signAndSend(signerName) {
			perform(action, context)
		}
	}

	suspend fun signAndSend(signerName: AgentName, build: () -> SsmCmd): InvokeReturn? {
		return build().let {
			sign(it, signerName)
		}.let {
			send(it)
		}
	}

	fun sign(command: SsmCmd, signerName: AgentName): SsmCmdSigned {
		return ssmCmdSigner.sign(command, signerName)
	}

	suspend fun send(ssmCommandSigned: SsmCmdSigned): InvokeReturn? {
		return ssmRequester.invoke(ssmCommandSigned)
	}
}
