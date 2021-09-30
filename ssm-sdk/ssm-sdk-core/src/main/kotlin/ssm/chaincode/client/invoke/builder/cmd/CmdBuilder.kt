package ssm.chaincode.client.invoke.builder.cmd

import ssm.chaincode.client.model.SsmCmd
import ssm.chaincode.client.model.SsmCmdName
import ssm.chaincode.client.model.SsmCmdSigned
import ssm.chaincode.client.model.SsmCmdSignerSha256RSASigner
import ssm.sdk.json.JsonUtils.toJson
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
		val json = toJson(value)
		val toSign = valueToSign(json)
		return SsmCmd(json = json, command = commandName, performAction = performAction, valueToSign = toSign)
	}

	private fun valueToSign(json: String): String {
		return performAction?.let {
			performAction + json
		} ?: json
	}
}
