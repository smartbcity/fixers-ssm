package ssm.sdk.core.signer

import java.util.Base64
import ssm.sdk.client.model.SsmCmd
import ssm.sdk.client.model.SsmCmdSigned
import ssm.sdk.client.model.SsmCmdSigner
import ssm.sdk.sign.crypto.Sha256RSASigner
import ssm.sdk.sign.model.Signer

open class SsmCmdSignerSha256RSASigner: SsmCmdSigner {
	override fun sign(ssmCommand: SsmCmd, signer: Signer): SsmCmdSigned {
		val signature = Sha256RSASigner.rsaSign(ssmCommand.valueToSign, signer.pair.private)
		return SsmCmdSigned(
			cmd = ssmCommand,
			signer = signer.name,
			signature = Base64.getEncoder().encodeToString(signature)
		)
	}
}
