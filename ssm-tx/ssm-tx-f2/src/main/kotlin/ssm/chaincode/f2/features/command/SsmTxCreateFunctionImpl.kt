package ssm.chaincode.f2.features.command

import ssm.chaincode.dsl.config.SsmChaincodeConfig
import ssm.chaincode.f2.utils.SsmException
import ssm.chaincode.f2.utils.ssmF2Function
import ssm.sdk.sign.SignerAdminProvider
import ssm.tx.dsl.features.ssm.SsmTxCreateFunction
import ssm.tx.dsl.features.ssm.SsmCreateResult

class SsmTxCreateFunctionImpl {

	fun ssmTxCreateFunction(
		config: SsmChaincodeConfig,
		signerProvider: SignerAdminProvider
	): SsmTxCreateFunction = ssmF2Function(config) { cmd, ssmClient ->
		try {
			val adminSigner = signerProvider.get()
			ssmClient.create(adminSigner, cmd.ssm).let {
				SsmCreateResult(listOf(it!!))
			}
		} catch (e: Exception) {
			throw SsmException(e)
		}
	}
}
