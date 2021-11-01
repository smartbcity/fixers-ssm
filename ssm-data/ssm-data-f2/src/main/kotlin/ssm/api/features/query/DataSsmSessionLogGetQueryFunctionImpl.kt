package ssm.api.features.query

import f2.dsl.fnc.f2Function
import ssm.api.extentions.getSessionLogs
import ssm.api.extentions.getTransaction
import ssm.data.dsl.config.SsmDataConfig
import ssm.data.dsl.features.query.DataSsmSessionLogGetQueryFunction
import ssm.data.dsl.features.query.DataSsmSessionLogGetQueryResult
import ssm.data.dsl.model.DataSsmSessionState

class DataSsmSessionLogGetQueryFunctionImpl(
	private val config: SsmDataConfig
) {
	fun dataSsmSessionLogGetQueryFunction(): DataSsmSessionLogGetQueryFunction =
		f2Function { cmd ->
			val logs = cmd.sessionId.getSessionLogs(config)
			val transaction = cmd.txId.getTransaction(config)
			logs.firstOrNull { log -> log.txId == cmd.txId }
				?.let { sessionState ->
					DataSsmSessionState(
						details = sessionState.state,
						transaction = transaction
					)
				}.let {
					DataSsmSessionLogGetQueryResult(it)
				}
		}
}
