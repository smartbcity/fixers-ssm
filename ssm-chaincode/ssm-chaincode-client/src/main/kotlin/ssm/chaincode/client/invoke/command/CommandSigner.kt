package ssm.chaincode.client.invoke.command

import java.util.Base64
import ssm.sdk.json.JsonUtils.toJson
import ssm.sdk.sign.crypto.Sha256RSASigner
import ssm.sdk.sign.model.Signer

open class CommandSigner<T>(private val signer: Signer, val commandName: String, private val value: T) {
	@Throws(Exception::class)
	operator fun invoke(): InvokeArgs {
		val json = toJson(value)
		val toSign = valueToSign(json)
		val signature = Sha256RSASigner.rsaSign(toSign, signer.pair.private)
		val b64Signature = Base64.getEncoder().encodeToString(signature)
		return buildArgs(commandName, json, signer.name, b64Signature)
	}

	@Throws(Exception::class)
	protected open fun valueToSign(json: String): String {
		return json
	}

	@Throws(Exception::class)
	protected open fun buildArgs(command: String, json: String, signer: String, b64Signature: String): InvokeArgs {
		return InvokeArgs(command, listOf(json, signer, b64Signature))
	}
}
