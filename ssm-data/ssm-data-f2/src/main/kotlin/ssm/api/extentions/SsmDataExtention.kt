package ssm.api.extentions

import f2.dsl.fnc.invoke
import ssm.chaincode.dsl.blockchain.Transaction
import ssm.chaincode.dsl.blockchain.TransactionId
import ssm.chaincode.dsl.model.Ssm
import ssm.chaincode.dsl.model.SsmSessionState
import ssm.chaincode.dsl.model.SsmSessionStateDTO
import ssm.chaincode.dsl.model.SsmSessionStateLog
import ssm.chaincode.dsl.query.SsmGetSessionLogsQuery
import ssm.chaincode.dsl.query.SsmGetTransactionQuery
import ssm.chaincode.f2.query.SsmGetSessionLogsQueryFunctionImpl
import ssm.chaincode.f2.query.SsmGetTransactionQueryFunctionImpl
import ssm.data.dsl.config.DataSsmConfig
import ssm.data.dsl.model.DataSsm
import ssm.data.dsl.model.DataSsmSession
import ssm.data.dsl.model.DataSsmSessionId
import ssm.data.dsl.model.DataSsmSessionState
import ssm.data.dsl.model.TxChannel


fun Ssm.toDataSsm(config: DataSsmConfig): DataSsm {
	return DataSsm(
		ssm = this,
		version = config.ssmVersion,
		channel = TxChannel(config.channelId)
	)
}

suspend fun SsmSessionStateDTO.toDataSession(
	config: DataSsmConfig,
	bearerToken: String?,
): DataSsmSession {
	val sessionLogs = session.getSessionLogs(config, bearerToken)

	val firstTransactionId = sessionLogs.minByOrNull { sessionLog -> sessionLog.state.iteration }?.txId
	val lastTransactionId = sessionLogs.maxByOrNull { sessionLog -> sessionLog.state.iteration }?.txId

	val firstTransaction = firstTransactionId.getTransaction(config, bearerToken)
	val lastTransaction = lastTransactionId.getTransaction(config, bearerToken)

	return this.toDataSession(config, firstTransaction, lastTransaction)
}

fun SsmSessionStateDTO.toDataSession(
	config: DataSsmConfig,  firstTransaction: Transaction?, lastTransaction: Transaction?
): DataSsmSession {
	return DataSsmSession(
		id = this.session,
		state = DataSsmSessionState(
			details = this as SsmSessionState,
			transaction = lastTransaction
		),
		channel = TxChannel(config.channelId),
		transaction = firstTransaction
	)
}

suspend fun DataSsmSessionId.getSessionLogs(
	config: DataSsmConfig,
	bearerToken: String?,
): List<SsmSessionStateLog> {
	val query = SsmGetSessionLogsQuery(
		session = this,
		bearerToken = bearerToken
	)
	return try {
		SsmGetSessionLogsQueryFunctionImpl().ssmGetSessionLogsQueryFunction(config.chaincode).invoke(query).logs
	} catch (e: Exception) {
		e.printStackTrace()
		emptyList()
	}
}

suspend fun TransactionId?.getTransaction(
	config: DataSsmConfig,
	bearerToken: String?,
): Transaction? {
	return this?.let {
		val query = SsmGetTransactionQuery(
			id = this,
			bearerToken = bearerToken
		)
		SsmGetTransactionQueryFunctionImpl().ssmGetTransactionQueryFunction(config.chaincode)
			.invoke(query).transaction
	}
}
