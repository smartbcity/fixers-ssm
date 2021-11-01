@file:JvmName("SignerUtils")

package ssm.sdk.client.extention

import ssm.chaincode.dsl.model.Agent
import ssm.sdk.sign.model.Signer

fun Signer.asAgent(): Agent {
	return Agent(
		this.name,
		this.pair.public.encoded
	)
}
