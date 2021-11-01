package ssm.chaincode.f2.features.command

import ssm.chaincode.dsl.config.SsmChaincodeConfig
import ssm.chaincode.f2.utils.ssmF2Function
import ssm.sdk.sign.SignerAdminProvider
import ssm.tx.dsl.features.ssm.SsmTxSessionStartFunction
import ssm.tx.dsl.features.ssm.SsmSessionStartResult

class SsmTxSessionStartFunctionImpl {

	fun ssmTxSessionStartFunction(
		config: SsmChaincodeConfig,
		signerAdminProvider: SignerAdminProvider
	): SsmTxSessionStartFunction = ssmF2Function(config) { cmd, ssmClient ->
		ssmClient.start(signerAdminProvider.get(), cmd.session)!!.let { result ->
			SsmSessionStartResult(
				transactionId = result.transactionId,
			)
		}
	}
}
