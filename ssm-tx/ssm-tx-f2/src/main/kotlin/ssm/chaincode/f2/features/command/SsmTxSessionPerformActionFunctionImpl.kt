package ssm.chaincode.f2.features.command

import ssm.chaincode.dsl.config.ChaincodeSsmConfig
import ssm.chaincode.f2.utils.ssmF2Function
import ssm.sdk.sign.SignerUserProvider
import ssm.tx.dsl.features.ssm.SsmSessionPerformActionResult
import ssm.tx.dsl.features.ssm.SsmTxSessionPerformActionFunction

class SsmTxSessionPerformActionFunctionImpl {

	fun ssmTxSessionPerformActionFunction(
		config: ChaincodeSsmConfig,
		signerProvider: SignerUserProvider
	): SsmTxSessionPerformActionFunction = ssmF2Function(config) { cmd, ssmClient ->
		ssmClient.sendPerform(signerProvider.get(), cmd.action, cmd.context)!!.let { result ->
			SsmSessionPerformActionResult(result.transactionId)
		}
	}
}
