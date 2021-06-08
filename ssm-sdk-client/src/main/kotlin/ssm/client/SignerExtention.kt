@file:JvmName("SignerUtils")
package ssm.client

import ssm.client.sign.model.Signer
import ssm.dsl.SsmAgentBase

fun Signer.asAgent(): SsmAgentBase {
    return SsmAgentBase(
            this.name,
            this.pair.public.encoded
    )
}