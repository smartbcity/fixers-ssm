package ssm.sdk.client.model

import ssm.sdk.sign.model.Signer

interface SsmCmdSigner {
	fun sign(ssmCommand: SsmCmd, signer: Signer): SsmCmdSigned
}
