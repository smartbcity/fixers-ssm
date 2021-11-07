package ssm.sdk.core

import ssm.chaincode.dsl.model.Agent
import ssm.chaincode.dsl.model.Ssm
import ssm.chaincode.dsl.model.SsmContext
import ssm.chaincode.dsl.model.SsmSession
import ssm.sdk.client.SsmRequester
import ssm.sdk.client.invoke.command.CreateCmd
import ssm.sdk.client.invoke.command.PerformCmd
import ssm.sdk.client.invoke.command.RegisterCmd
import ssm.sdk.client.invoke.command.StartCmd
import ssm.sdk.client.model.InvokeReturn
import ssm.sdk.client.model.SsmCmd
import ssm.sdk.client.model.SsmCmdSigned
import ssm.sdk.client.model.SsmCmdSigner
import ssm.sdk.sign.model.Signer
import ssm.sdk.sign.model.SignerAdmin

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


	suspend fun sendRegisterUser(signer: SignerAdmin, agent: Agent): InvokeReturn? {
		return signAndSend(signer) {
			registerUser(agent)
		}
	}

	suspend fun sendCreate(signer: SignerAdmin, ssm: Ssm): InvokeReturn? {
		return signAndSend(signer) {
			create(ssm)
		}
	}

	suspend fun sendStart(signer: SignerAdmin, session: SsmSession): InvokeReturn? {
		return signAndSend(signer) {
			start(session)
		}
	}

	suspend fun sendPerform(signer: Signer, action: String, context: SsmContext): InvokeReturn? {
		return signAndSend(signer) {
			perform(action, context)
		}
	}

	suspend fun signAndSend(signer: Signer, build: () -> SsmCmd): InvokeReturn? {
		return build().let {
			sign(it, signer)
		}.let {
			send(it)
		}
	}

	fun sign(command: SsmCmd, signer: Signer): SsmCmdSigned {
		return ssmCmdSigner.sign(command, signer)
	}

	suspend fun send(ssmCommandSigned: SsmCmdSigned): InvokeReturn? {
		return ssmRequester.invoke(ssmCommandSigned)
	}
}
