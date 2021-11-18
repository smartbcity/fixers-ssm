package ssm.api.extentions

import f2.dsl.fnc.invokeWith
import ssm.chaincode.dsl.blockchain.Transaction
import ssm.chaincode.dsl.blockchain.TransactionId
import ssm.chaincode.dsl.model.SessionName
import ssm.chaincode.dsl.model.Ssm
import ssm.chaincode.dsl.model.SsmSessionStateLog
import ssm.chaincode.dsl.model.uri.ChaincodeUri
import ssm.chaincode.dsl.model.uri.DEFAULT_VERSION
import ssm.chaincode.dsl.model.uri.SsmUri
import ssm.chaincode.dsl.model.uri.toSsmUri
import ssm.chaincode.dsl.query.SsmGetSessionLogsQuery
import ssm.chaincode.dsl.query.SsmGetSessionLogsQueryFunction
import ssm.chaincode.dsl.query.SsmGetTransactionQuery
import ssm.chaincode.dsl.query.SsmGetTransactionQueryFunction
import ssm.data.dsl.model.DataChannel
import ssm.data.dsl.model.DataSsm

fun Ssm.toDataSsm(burst: ChaincodeUri): DataSsm {
	return DataSsm(
		ssm = this,
		version = DEFAULT_VERSION,
		channel = DataChannel(burst.channelId),
		uri = burst.toSsmUri(this.name)
	)
}

fun Ssm.toDataSsm(ssm: SsmUri): DataSsm {
	return DataSsm(
		ssm = this,
		version = ssm.ssmVersion,
		channel = DataChannel(ssm.channelId),
		uri = ssm
	)
}

suspend fun SessionName.getSessionLogs(
	ssmUri: SsmUri,
	ssmGetSessionLogsQueryFunction: SsmGetSessionLogsQueryFunction,
): List<SsmSessionStateLog> {
	return try {
		SsmGetSessionLogsQuery(
			sessionName = this,
			ssmUri = ssmUri
		).invokeWith(ssmGetSessionLogsQueryFunction).logs
	} catch (e: Exception) {
		e.printStackTrace()
		emptyList()
	}
}

suspend fun TransactionId?.getTransaction(
	ssmGetTransactionQueryFunction: SsmGetTransactionQueryFunction,
): Transaction? {
	return this?.let { transactionId ->
		SsmGetTransactionQuery(
			id = transactionId,
		).invokeWith(ssmGetTransactionQueryFunction).item
	}
}
