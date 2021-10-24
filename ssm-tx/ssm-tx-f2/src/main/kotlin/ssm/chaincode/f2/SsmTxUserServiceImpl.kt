package ssm.chaincode.f2

import ssm.chaincode.dsl.config.SsmChaincodeConfig
import ssm.chaincode.f2.features.command.SsmTxSessionPerformActionFunctionImpl
import ssm.sdk.sign.SignerProvider
import ssm.tx.dsl.SsmTxUserFunctions
import ssm.tx.dsl.features.ssm.SsmTxSessionPerformActionFunction

class SsmTxUserServiceImpl(
	private val ssmTxSessionPerformActionFunction: SsmTxSessionPerformActionFunctionImpl =
		SsmTxSessionPerformActionFunctionImpl(),
	private val signerProvider: SignerProvider
) : SsmTxUserFunctions {


	override fun ssmTxSessionPerformActionFunction(config: SsmChaincodeConfig): SsmTxSessionPerformActionFunction {
		return ssmTxSessionPerformActionFunction.ssmTxSessionPerformActionFunction(config, signerProvider)
	}

}
