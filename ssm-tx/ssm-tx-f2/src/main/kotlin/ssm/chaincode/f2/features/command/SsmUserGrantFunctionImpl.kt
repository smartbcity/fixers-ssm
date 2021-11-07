package ssm.chaincode.f2.features.command

import ssm.chaincode.dsl.config.SsmChaincodeConfig
import ssm.chaincode.f2.utils.SsmException
import ssm.chaincode.f2.utils.ssmF2Function
import ssm.sdk.sign.SignerAdminProvider
import ssm.tx.dsl.features.user.SsmTxUserGrantFunction
import ssm.tx.dsl.features.user.SsmUserGrantedResult

class SsmUserGrantFunctionImpl {

	fun ssmUserGrantFunction(
		config: SsmChaincodeConfig, signerAdminProvider: SignerAdminProvider
	): SsmTxUserGrantFunction = ssmF2Function(config) { cmd, ssmClient ->
		try {
			ssmClient.sendRegisterUser(signerAdminProvider.get(), cmd.agent)!!.let { result ->
				SsmUserGrantedResult(
					transactionId = result.transactionId,
				)
			}
		} catch (e: Exception) {
			throw SsmException(e)
		}
	}
}
