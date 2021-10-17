package ssm.sdk.client.model

import ssm.sdk.sign.model.SignerName

enum class SsmCmdName(val value: String) {
	START("start"),
	CREATE("create"),
	PERFORM("perform"),
	REGISTER("register")
}

data class SsmCmd(
	val json: String,
	val command: SsmCmdName,
	val performAction: String? = null,
	val valueToSign: String,
)

data class SsmCmdSigned(
	val cmd: SsmCmd,
	val signature: String,
	val signer: SignerName,
)

fun SsmCmdSigned.buildArgs(): InvokeArgs {
	return InvokeArgs(cmd.command.value,
		listOfNotNull(cmd.performAction, cmd.json, signer, signature))
}
