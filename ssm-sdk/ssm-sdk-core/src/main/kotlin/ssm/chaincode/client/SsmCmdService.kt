package ssm.chaincode.client

import java.util.concurrent.CompletableFuture
import ssm.chaincode.client.invoke.command.CreateCmdBuilder
import ssm.chaincode.client.invoke.command.PerformCmdBuilder
import ssm.chaincode.client.invoke.command.RegisterCmdBuilder
import ssm.chaincode.client.model.SsmCmd
import ssm.chaincode.client.model.SsmCmdSigned
import ssm.chaincode.client.model.SsmCmdSignerSha256RSASigner
import ssm.chaincode.client.invoke.command.StartCmdSigner
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
