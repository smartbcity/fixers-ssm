package ssm.chaincode.f2

import ssm.chaincode.dsl.config.SsmChaincodeConfig
import ssm.chaincode.f2.commons.SsmException
import ssm.chaincode.f2.commons.ssmF2Function
import ssm.sdk.sign.model.SignerAdmin
import ssm.tx.dsl.features.user.SsmUserRegisterFunction
import ssm.tx.dsl.features.user.SsmUserRegisteredResult

class SsmUserRegisterFunctionImpl {

	fun ssmUserRegisterFunction(
		config: SsmChaincodeConfig,
		signerAdmin: SignerAdmin
	): SsmUserRegisterFunction = ssmF2Function(config) { cmd, ssmClient ->
		try {
			ssmClient.registerUser(signerAdmin, cmd.agent)!!.let {
				SsmUserRegisteredResult(it)
			}
		} catch (e: Exception) {
			throw SsmException(e)
		}

	}
}
