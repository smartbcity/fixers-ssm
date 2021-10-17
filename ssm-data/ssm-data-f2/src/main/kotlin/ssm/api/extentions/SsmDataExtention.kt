package ssm.api.extentions

import f2.dsl.fnc.invoke
import ssm.chaincode.dsl.blockchain.Transaction
import ssm.chaincode.dsl.blockchain.TransactionId
import ssm.chaincode.dsl.model.Ssm
import ssm.chaincode.dsl.model.SsmSessionState
import ssm.chaincode.dsl.model.SsmSessionStateDTO
import ssm.chaincode.dsl.model.SsmSessionStateLog
import ssm.chaincode.dsl.model.uri.ChaincodeUri
import ssm.chaincode.dsl.model.uri.ChaincodeUriBurstDTO
import ssm.chaincode.dsl.model.uri.DEFAULT_VERSION
import ssm.chaincode.dsl.model.uri.SsmUri
import ssm.chaincode.dsl.model.uri.SsmUriBurstDTO
import ssm.chaincode.dsl.model.uri.burstChaincode
import ssm.chaincode.dsl.model.uri.burstSsmUri
import ssm.chaincode.dsl.model.uri.compact
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

fun Ssm.toDataSsm(burst: ChaincodeUriBurstDTO): DataSsm {
	return DataSsm(
		ssm = this,
		version = DEFAULT_VERSION,
		channel = TxChannel(burst.channelId),
		uri = burst.compact(this.name)
	)
}

fun Ssm.toDataSsm(ssm: SsmUriBurstDTO): DataSsm {
	return DataSsm(
		ssm = this,
		version = ssm.ssmVersion,
		channel = TxChannel(ssm.channelId),
		uri = ssm.compact()
	)
}

fun Ssm.toDataSsm(ssm: SsmUri): DataSsm {
	val values = ssm.burstSsmUri()!!
	return toDataSsm(values)
}

suspend fun SsmSessionStateDTO.toDataSession(
	config: DataSsmConfig,
	ssm: SsmUri,
	bearerToken: String?,
): DataSsmSession {
	val sessionLogs = session.getSessionLogs(config, bearerToken)

	val transactions = sessionLogs.map { it.txId.getTransaction(config, bearerToken) }.filterNotNull()
	val firstTransaction = transactions.minByOrNull { transaction ->
		transaction.timestamp
	}
	val lastTransaction = transactions.maxByOrNull { transaction ->
		transaction.timestamp
	}

	return this.toDataSession(ssm, firstTransaction, lastTransaction, transactions)
}

fun SsmSessionStateDTO.toDataSession(
	ssm: SsmUri, firstTransaction: Transaction?, lastTransaction: Transaction?, transactions: List<Transaction>
): DataSsmSession {
	val values = ssm.burstSsmUri()!!
	return DataSsmSession(
		ssmUri = ssm,
		id = this.session,
		state = DataSsmSessionState(
			details = this as SsmSessionState,
			transaction = lastTransaction
		),
		channel = TxChannel(values.channelId),
		transaction = firstTransaction,
		transactions = transactions
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
