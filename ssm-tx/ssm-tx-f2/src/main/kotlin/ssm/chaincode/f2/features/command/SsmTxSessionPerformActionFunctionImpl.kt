package ssm.chaincode.f2.features.command

import ssm.chaincode.dsl.config.SsmChaincodeConfig
import ssm.chaincode.f2.utils.ssmF2Function
import ssm.sdk.sign.SignerProvider
import ssm.tx.dsl.features.ssm.SsmSessionPerformActionResult
import ssm.tx.dsl.features.ssm.SsmTxSessionPerformActionFunction

class SsmTxSessionPerformActionFunctionImpl {

	fun ssmTxSessionPerformActionFunction(
		config: SsmChaincodeConfig,
		signerProvider: SignerProvider
	): SsmTxSessionPerformActionFunction = ssmF2Function(config) { cmd, ssmClient ->
		ssmClient.sendPerform(signerProvider.get(), cmd.action, cmd.context)!!.let { result ->
			SsmSessionPerformActionResult(result.transactionId)
		}
	}
}
