package ssm.chaincode.f2

import ssm.chaincode.dsl.config.SsmChaincodeConfig
import ssm.chaincode.f2.commons.SsmException
import ssm.chaincode.f2.commons.ssmF2Function
import ssm.sdk.sign.model.SignerAdmin
import ssm.tx.dsl.features.user.SsmUserGrantFunction
import ssm.tx.dsl.features.user.SsmUserGrantedResult

class SsmUserGrantFunctionImpl {

	fun ssmUserGrantFunction(
		config: SsmChaincodeConfig, signerAdmin: SignerAdmin
	): SsmUserGrantFunction = ssmF2Function(config) { cmd, ssmClient ->
		try {
			ssmClient.registerUser(signerAdmin, cmd.agent)!!.let {
				SsmUserGrantedResult(it)
			}
		} catch (e: Exception) {
			throw SsmException(e)
		}
	}
}
