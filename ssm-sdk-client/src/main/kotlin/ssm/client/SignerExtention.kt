@file:JvmName("SignerUtils")
package ssm.client

import ssm.client.domain.Signer
import ssm.dsl.Agent

fun Signer.asAgent(): Agent {
    return Agent(
            this.name,
            this.pair.public.encoded
    )
}