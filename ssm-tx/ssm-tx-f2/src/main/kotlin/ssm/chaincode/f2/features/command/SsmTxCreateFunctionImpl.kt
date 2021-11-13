package ssm.chaincode.f2.features.command

import ssm.chaincode.dsl.config.ChaincodeSsmConfig
import ssm.chaincode.f2.utils.SsmException
import ssm.chaincode.f2.utils.ssmF2Function
import ssm.sdk.sign.SignerAdminProvider
import ssm.tx.dsl.features.ssm.SsmCreateResult
import ssm.tx.dsl.features.ssm.SsmTxCreateFunction

class SsmTxCreateFunctionImpl {

	fun ssmTxCreateFunction(
		config: ChaincodeSsmConfig,
		signerProvider: SignerAdminProvider
	): SsmTxCreateFunction = ssmF2Function(config) { cmd, ssmClient ->
		try {
			val adminSigner = signerProvider.get()
			ssmClient.sendCreate(adminSigner, cmd.ssm).let {
				SsmCreateResult(
					transactionId = it!!.transactionId,
				)
			}
		} catch (e: Exception) {
			throw SsmException(e)
		}
	}
}
