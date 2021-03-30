@file:JvmName("SignerUtils")
package ssm.client

import ssm.client.sign.model.Signer
import ssm.dsl.SsmAgent

fun Signer.asAgent(): SsmAgent {
    return SsmAgent(
            this.name,
            this.pair.public.encoded
    )
}