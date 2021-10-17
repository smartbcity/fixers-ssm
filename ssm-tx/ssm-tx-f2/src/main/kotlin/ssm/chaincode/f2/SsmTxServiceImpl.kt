package ssm.chaincode.f2

import ssm.chaincode.dsl.config.SsmChaincodeConfig
import ssm.tx.dsl.SsmTxUserFunctions
import ssm.tx.dsl.features.user.SsmUserGrantFunction
import ssm.tx.dsl.features.user.SsmUserRegisterFunction

class SsmTxServiceImpl : SsmTxUserFunctions {

	override fun ssmUserGrantFunction(config: SsmChaincodeConfig): SsmUserGrantFunction {
		TODO("Not yet implemented")
	}

	override fun ssmUserRegisterFunction(config: SsmChaincodeConfig): SsmUserRegisterFunction {
		TODO("Not yet implemented")
	}

}
