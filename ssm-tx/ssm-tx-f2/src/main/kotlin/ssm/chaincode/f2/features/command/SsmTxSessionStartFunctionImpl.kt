package ssm.chaincode.f2.features.command

import ssm.chaincode.dsl.config.ChaincodeSsmConfig
import ssm.chaincode.f2.utils.ssmF2Function
import ssm.sdk.sign.SignerAdminProvider
import ssm.tx.dsl.features.ssm.SsmSessionStartResult
import ssm.tx.dsl.features.ssm.SsmTxSessionStartFunction

class SsmTxSessionStartFunctionImpl {

	fun ssmTxSessionStartFunction(
		config: ChaincodeSsmConfig,
		signerAdminProvider: SignerAdminProvider
	): SsmTxSessionStartFunction = ssmF2Function(config) { cmd, ssmClient ->
		ssmClient.sendStart(signerAdminProvider.get(), cmd.session)!!.let { result ->
			SsmSessionStartResult(
				transactionId = result.transactionId,
			)
		}
	}
}
