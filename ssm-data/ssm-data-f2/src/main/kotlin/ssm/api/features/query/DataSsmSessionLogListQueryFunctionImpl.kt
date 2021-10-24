package ssm.api.features.query

import f2.dsl.fnc.f2Function
import ssm.api.extentions.getSessionLogs
import ssm.api.extentions.getTransaction
import ssm.data.dsl.config.SsmDataConfig
import ssm.data.dsl.features.query.DataSsmSessionLogListQueryFunction
import ssm.data.dsl.features.query.DataSsmSessionLogListQueryResult
import ssm.data.dsl.model.DataSsmSessionState

class DataSsmSessionLogListQueryFunctionImpl(
	private val config: SsmDataConfig
) {

	fun dataSsmSessionLogListQueryFunction(): DataSsmSessionLogListQueryFunction =
		f2Function { cmd ->
			val logs = cmd.sessionId.getSessionLogs(config, cmd.bearerToken)

			logs.map { log ->
				val transaction = log.txId.getTransaction(config, cmd.bearerToken)
				DataSsmSessionState(
					details = log.state,
					transaction = transaction
				)
			}.let {
				DataSsmSessionLogListQueryResult(it)
			}
		}
}
