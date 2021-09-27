package ssm.chaincode.f2.query

import ssm.chaincode.dsl.SsmChaincodeQueries
import ssm.chaincode.dsl.config.SsmChaincodeConfig
import ssm.chaincode.dsl.query.SsmGetAdminFunction
import ssm.chaincode.dsl.query.SsmGetQueryFunction
import ssm.chaincode.dsl.query.SsmGetSessionLogsQueryFunction
import ssm.chaincode.dsl.query.SsmGetSessionQueryFunction
import ssm.chaincode.dsl.query.SsmGetTransactionQueryFunction
import ssm.chaincode.dsl.query.SsmGetUserFunction
import ssm.chaincode.dsl.query.SsmListAdminQueryFunction
import ssm.chaincode.dsl.query.SsmListSessionQueryFunction
import ssm.chaincode.dsl.query.SsmListSsmQueryFunction
import ssm.chaincode.dsl.query.SsmListUserQueryFunction

class SsmChaincodeQueryFunctions: SsmChaincodeQueries {
	override fun ssmGetAdminFunction(config: SsmChaincodeConfig): SsmGetAdminFunction {
		return SsmGetAdminFunctionImpl().ssmGetAdminFunction(config)
	}

	override fun ssmGetQueryFunction(config: SsmChaincodeConfig): SsmGetQueryFunction {
		return SsmGetQueryFunctionImpl().ssmGetQueryFunction(config)
	}

	override fun ssmGetSessionLogsQueryFunction(config: SsmChaincodeConfig): SsmGetSessionLogsQueryFunction {
		return SsmGetSessionLogsQueryFunctionImpl().ssmGetSessionLogsQueryFunction(config)
	}

	override fun ssmGetSessionQueryFunction(config: SsmChaincodeConfig): SsmGetSessionQueryFunction {
		return SsmGetSessionQueryFunctionImpl().ssmGetSessionQueryFunction(config)
	}

	override fun ssmGetTransactionQueryFunction(config: SsmChaincodeConfig): SsmGetTransactionQueryFunction {
		return SsmGetTransactionQueryFunctionImpl().ssmGetTransactionQueryFunction(config)
	}

	override fun ssmGetUserFunction(config: SsmChaincodeConfig): SsmGetUserFunction {
		return SsmGetUserFunctionImpl().ssmGetUserFunction(config)
	}

	override fun ssmListAdminQueryFunction(config: SsmChaincodeConfig): SsmListAdminQueryFunction {
		return SsmListAdminQueryFunctionImpl().ssmListAdminQueryFunction(config)
	}

	override fun ssmListSessionQueryFunction(config: SsmChaincodeConfig): SsmListSessionQueryFunction {
		return SsmListSessionQueryFunctionImpl().ssmListSessionQueryFunction(config)
	}

	override fun ssmListSsmQueryFunction(config: SsmChaincodeConfig): SsmListSsmQueryFunction {
		return SsmListSsmQueryFunctionImpl().ssmListSsmQueryFunction(config)
	}

	override fun ssmListUserQueryFunction(config: SsmChaincodeConfig): SsmListUserQueryFunction {
		return SsmListUserQueryFunctionImpl().ssmListUserQueryFunction(config)
	}
}
