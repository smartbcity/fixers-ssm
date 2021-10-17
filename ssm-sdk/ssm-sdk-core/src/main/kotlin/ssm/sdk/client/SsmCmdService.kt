package ssm.sdk.client

import ssm.sdk.client.command.CreateCmdBuilder
import ssm.sdk.client.command.PerformCmdBuilder
import ssm.sdk.client.command.RegisterCmdBuilder
import ssm.sdk.client.model.SsmCmd
import ssm.sdk.client.model.SsmCmdSigned
import ssm.sdk.client.model.SsmCmdSignerSha256RSASigner
import ssm.sdk.client.command.StartCmdSigner
import ssm.chaincode.dsl.model.InvokeReturn
import ssm.chaincode.dsl.model.Ssm
import ssm.chaincode.dsl.model.SsmAgent
import ssm.chaincode.dsl.model.SsmContext
import ssm.chaincode.dsl.model.SsmSession
import ssm.sdk.sign.model.Signer

class SsmCmdService(
	private val ssmRequester: SsmRequester,
	private val ssmCmdSigner: SsmCmdSignerSha256RSASigner
) {

	fun registerUser(agent: SsmAgent): SsmCmd {
		val cmd = RegisterCmdBuilder(agent)
		return cmd.commandToSign()
	}

	fun create(ssm: Ssm): SsmCmd {
		val cmd = CreateCmdBuilder(ssm)
		return cmd.commandToSign()
	}

	fun start(session: SsmSession): SsmCmd {
		val cmd = StartCmdSigner(session)
		return cmd.commandToSign()
	}

	fun perform(action: String, context: SsmContext): SsmCmd {
		val cmd = PerformCmdBuilder(action, context)
		return cmd.commandToSign()
	}

	fun sign(command: SsmCmd, signer: Signer): SsmCmdSigned {
		return ssmCmdSigner.sign(command, signer)
	}

	suspend fun send(ssmCommandSigned: SsmCmdSigned): InvokeReturn? {
		return ssmRequester.invoke(ssmCommandSigned)
	}
}
