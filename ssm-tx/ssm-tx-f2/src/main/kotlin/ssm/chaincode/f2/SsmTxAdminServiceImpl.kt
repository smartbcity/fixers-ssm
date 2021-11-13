package ssm.chaincode.f2

import ssm.chaincode.dsl.config.ChaincodeSsmConfig
import ssm.chaincode.f2.features.command.SsmInitializeFunctionImpl
import ssm.chaincode.f2.features.command.SsmTxCreateFunctionImpl
import ssm.chaincode.f2.features.command.SsmTxSessionStartFunctionImpl
import ssm.chaincode.f2.features.command.SsmUserGrantFunctionImpl
import ssm.chaincode.f2.features.command.SsmUserRegisterFunctionImpl
import ssm.sdk.sign.SignerAdminProvider
import ssm.tx.dsl.SsmTxAdminFunctions
import ssm.tx.dsl.features.ssm.SsmTxCreateFunction
import ssm.tx.dsl.features.ssm.SsmTxInitializeFunction
import ssm.tx.dsl.features.ssm.SsmTxSessionStartFunction
import ssm.tx.dsl.features.user.SsmTxUserGrantFunction
import ssm.tx.dsl.features.user.SsmTxUserRegisterFunction

class SsmTxAdminServiceImpl(
	private val signerAdminProvider: SignerAdminProvider,
	private val ssmUserGrantFunction: SsmUserGrantFunctionImpl = SsmUserGrantFunctionImpl(),
	private val ssmUserRegisterFunction: SsmUserRegisterFunctionImpl = SsmUserRegisterFunctionImpl(),
	private val ssmTxCreateFunction: SsmTxCreateFunctionImpl = SsmTxCreateFunctionImpl(),
	private val ssmTxSessionStartFunction: SsmTxSessionStartFunctionImpl = SsmTxSessionStartFunctionImpl(),
) : SsmTxAdminFunctions {

	override fun ssmTxUserGrantFunction(config: ChaincodeSsmConfig): SsmTxUserGrantFunction {
		return ssmUserGrantFunction.ssmUserGrantFunction(config, signerAdminProvider)
	}

	override fun ssmTxUserRegisterFunction(config: ChaincodeSsmConfig): SsmTxUserRegisterFunction {
		return ssmUserRegisterFunction.ssmUserRegisterFunction(config, signerAdminProvider)
	}


	override fun ssmTxCreateFunction(config: ChaincodeSsmConfig): SsmTxCreateFunction {
		return ssmTxCreateFunction.ssmTxCreateFunction(config, signerAdminProvider)
	}

	override fun ssmTxInitializeFunction(config: ChaincodeSsmConfig): SsmTxInitializeFunction {
		return SsmInitializeFunctionImpl(config, signerAdminProvider).ssmInitializeFunction()
	}

	override fun ssmTxSessionStartFunction(config: ChaincodeSsmConfig): SsmTxSessionStartFunction {
		return ssmTxSessionStartFunction.ssmTxSessionStartFunction(config, signerAdminProvider)
	}


}
