package ssm.chaincode.f2

import ssm.chaincode.dsl.config.ChaincodeSsmConfig
import ssm.chaincode.f2.features.command.SsmTxSessionPerformActionFunctionImpl
import ssm.sdk.sign.SignerUserProvider
import ssm.tx.dsl.SsmTxUserFunctions
import ssm.tx.dsl.features.ssm.SsmTxSessionPerformActionFunction

class SsmTxUserServiceImpl(
	private val ssmTxSessionPerformActionFunction: SsmTxSessionPerformActionFunctionImpl =
		SsmTxSessionPerformActionFunctionImpl(),
	private val signerProvider: SignerUserProvider
) : SsmTxUserFunctions {


	override fun ssmTxSessionPerformActionFunction(config: ChaincodeSsmConfig): SsmTxSessionPerformActionFunction {
		return ssmTxSessionPerformActionFunction.ssmTxSessionPerformActionFunction(config, signerProvider)
	}

}
