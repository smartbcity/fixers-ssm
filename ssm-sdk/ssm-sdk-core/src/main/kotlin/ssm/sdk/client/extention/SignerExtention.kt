@file:JvmName("SignerUtils")

package ssm.sdk.client.extention

import ssm.chaincode.dsl.model.SsmAgent
import ssm.sdk.sign.model.Signer

fun Signer.asAgent(): SsmAgent {
	return SsmAgent(
		this.name,
		this.pair.public.encoded
	)
}
