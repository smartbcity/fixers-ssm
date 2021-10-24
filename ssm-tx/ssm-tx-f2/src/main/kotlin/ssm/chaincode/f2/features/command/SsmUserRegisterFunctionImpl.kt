package ssm.chaincode.f2.features.command

import ssm.chaincode.dsl.config.SsmChaincodeConfig
import ssm.chaincode.f2.utils.SsmException
import ssm.chaincode.f2.utils.ssmF2Function
import ssm.sdk.sign.SignerAdminProvider
import ssm.tx.dsl.features.user.SsmTxUserRegisterFunction
import ssm.tx.dsl.features.user.SsmUserRegisteredResult

class SsmUserRegisterFunctionImpl {

	fun ssmUserRegisterFunction(
		config: SsmChaincodeConfig,
		signerAdminProvider: SignerAdminProvider
	): SsmTxUserRegisterFunction = ssmF2Function(config) { cmd, ssmClient ->
		try {
			ssmClient.registerUser(signerAdminProvider.get(), cmd.agent)!!.let {
				SsmUserRegisteredResult(it)
			}
		} catch (e: Exception) {
			throw SsmException(e)
		}

	}
}
