package ssm.sdk.client.invoke.builder

import ssm.sdk.client.invoke.command.SsmCmdName
import ssm.sdk.client.model.SsmCmd
import ssm.sdk.client.model.SsmCmdSigned
import ssm.sdk.core.signer.SsmCmdSignerSha256RSASigner
import ssm.sdk.json.JsonUtils
import ssm.sdk.sign.model.Signer

open class CmdBuilder<T>(
	private val value: T,
	private val commandName: SsmCmdName,
	private val performAction: String? = null) {

	operator fun invoke(signer: Signer): SsmCmdSigned {
		val cmd = commandToSign()
		return SsmCmdSignerSha256RSASigner().sign(cmd, signer)
	}

	fun commandToSign(): SsmCmd {
		val json = JsonUtils.toJson(value)
		val toSign = valueToSign(json)
		return SsmCmd(json = json, command = commandName, performAction = performAction, valueToSign = toSign)
	}

	private fun valueToSign(json: String): String {
		return performAction?.let {
			performAction + json
		} ?: json
	}
}
