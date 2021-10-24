package ssm.chaincode.f2.query

import ssm.chaincode.dsl.config.SsmChaincodeConfig
import ssm.chaincode.dsl.query.SsmGetSessionLogsQueryFunction
import ssm.chaincode.dsl.query.SsmGetSessionLogsQueryResult
import ssm.chaincode.f2.query.commons.ssmF2Function

class SsmGetSessionLogsQueryFunctionImpl {

	fun ssmGetSessionLogsQueryFunction(config: SsmChaincodeConfig): SsmGetSessionLogsQueryFunction =
		ssmF2Function(config) { cmd, ssmClient ->
			ssmClient.log(cmd.sessionName)
				.let {
					SsmGetSessionLogsQueryResult(
						ssmUri = "",
						sessionName = cmd.sessionName,
						logs = it
					)
				}
		}
}
