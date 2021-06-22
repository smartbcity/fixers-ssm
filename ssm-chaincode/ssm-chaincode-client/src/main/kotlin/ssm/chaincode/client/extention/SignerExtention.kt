@file:JvmName("SignerUtils")
package ssm.chaincode.client

import ssm.chaincode.dsl.SsmAgentBase
import ssm.sdk.sign.model.Signer

fun Signer.asAgent(): SsmAgentBase {
    return SsmAgentBase(
            this.name,
            this.pair.public.encoded
    )
}