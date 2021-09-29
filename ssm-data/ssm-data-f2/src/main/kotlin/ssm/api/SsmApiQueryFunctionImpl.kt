package ssm.api

import f2.dsl.fnc.f2Function
import f2.dsl.fnc.invoke
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.toList
import ssm.api.model.toTxSession
import ssm.api.model.toDataSsm
import ssm.chaincode.dsl.model.SsmSessionStateDTO
import ssm.chaincode.dsl.model.SsmSessionStateLog
import ssm.chaincode.dsl.blockchain.Transaction
import ssm.chaincode.dsl.blockchain.TransactionId
import ssm.chaincode.dsl.query.SsmGetQuery
import ssm.chaincode.dsl.query.SsmGetSessionLogsQuery
import ssm.chaincode.dsl.query.SsmGetSessionQuery
import ssm.chaincode.dsl.query.SsmGetTransactionQuery
import ssm.chaincode.f2.query.SsmGetQueryFunctionImpl
import ssm.chaincode.f2.query.SsmGetSessionLogsQueryFunctionImpl
import ssm.chaincode.f2.query.SsmGetSessionQueryFunctionImpl
import ssm.chaincode.f2.query.SsmGetTransactionQueryFunctionImpl
import ssm.couchdb.dsl.query.CouchdbSsmListQuery
import ssm.couchdb.dsl.query.CouchdbSsmSessionStateListQuery
import ssm.couchdb.f2.query.CouchdbSsmListQueryFunctionImpl
import ssm.couchdb.f2.query.CouchdbSsmSessionStateListQueryFunctionImpl
import ssm.data.dsl.config.DataSsmConfig
import ssm.data.dsl.features.query.DataSsmGetQueryResult
import ssm.data.dsl.features.query.DataSsmListQueryFunction
import ssm.data.dsl.features.query.DataSsmListQueryResult
import ssm.data.dsl.features.query.DataSsmSessionGetQueryFunction
import ssm.data.dsl.features.query.DataSsmSessionGetQueryResult
import ssm.data.dsl.features.query.DataSsmSessionListQueryFunction
import ssm.data.dsl.features.query.DataSsmSessionListQueryResult
import ssm.data.dsl.features.query.DataSsmSessionLogGetQueryFunction
import ssm.data.dsl.features.query.DataSsmSessionLogGetQueryResult
import ssm.data.dsl.features.query.DataSsmSessionLogListQueryFunction
import ssm.data.dsl.features.query.DataSsmSessionLogListQueryResult
import ssm.data.dsl.model.DataSsmSession
import ssm.data.dsl.model.DataSsmSessionId
import ssm.data.dsl.model.DataSsmSessionState

class SsmApiQueryFunctionImpl: ssm.data.dsl.SsmApiQueryFunctions {

	override fun dataSsmListQueryFunction(config: DataSsmConfig): DataSsmListQueryFunction = f2Function { query ->
		CouchdbSsmListQueryFunctionImpl(config.couchdb)
			.couchdbSsmListQueryFunction()
			.invoke(
				CouchdbSsmListQuery(
					chaincodeId = query.chaincodeId,
					channelId = query.channelId,
					pagination = null
				)
			).page.list.asFlow()
			.map { result ->
				result.toDataSsm()
			}.let {
				val dataSsmListQueryResult = DataSsmListQueryResult(it.toList())
				dataSsmListQueryResult
			}
	}

	override fun dataSsmGetQueryFunction(config: DataSsmConfig): ssm.data.dsl.features.query.DataSsmGetQueryFunction = f2Function { cmd ->
		val command = SsmGetQuery(
			name = cmd.ssm,
			bearerToken = cmd.bearerToken
		)

		SsmGetQueryFunctionImpl().ssmGetQueryFunction(config.chaincode).invoke(command)
			.ssm
			?.toDataSsm()
			.let(::DataSsmGetQueryResult)
	}

	override fun dataSsmSessionListQueryFunction(config: DataSsmConfig): DataSsmSessionListQueryFunction = f2Function { query ->
		val couchdbQuery = CouchdbSsmSessionStateListQuery(
			channelId = query.channelId,
			chaincodeId = query.chaincodeId,
			ssm = query.ssm
		)
		CouchdbSsmSessionStateListQueryFunctionImpl(config.couchdb).couchdbSsmSessionStateListQueryFunction().invoke(
			couchdbQuery
		).page.list
			.filter { sessionState -> sessionState.session.isNotBlank() }
			.map { sessionState -> sessionState.toTxSession(config, query.bearerToken) }.let {
				DataSsmSessionListQueryResult(it)
			}
	}

	override fun dataSsmSessionGetQueryFunction(config: DataSsmConfig): DataSsmSessionGetQueryFunction = f2Function { cmd ->
		val sessionQuery = SsmGetSessionQuery(
			name = cmd.sessionId,
			bearerToken = cmd.bearerToken
		)

		try {
			SsmGetSessionQueryFunctionImpl().ssmGetSessionQueryFunction(config.chaincode).invoke(sessionQuery)
				.session
				?.toTxSession(config, cmd.bearerToken).let {
					DataSsmSessionGetQueryResult(
						it
					)
				}
		} catch (e: Exception) {
			e.printStackTrace()
			DataSsmSessionGetQueryResult(null)
		}
	}

//	private fun getConfig(cmd: TxQueryDTO): DataSsmConfig {
//		return ssmConfigProperties.list[cmd.ssm]?.entries?.first()?.value
//			?: throw IllegalArgumentException("Configuration of SSM [${cmd.ssm}] not found")
//	}

	override fun dataSsmSessionLogListQueryFunction(config: DataSsmConfig): DataSsmSessionLogListQueryFunction =
		f2Function { cmd ->
			val logs = getSessionLogs(cmd.sessionId, config, cmd.bearerToken)

			logs.map { log ->
				val transaction = getTransaction(log.txId, config, cmd.bearerToken)
				DataSsmSessionState(
					details = log.state,
					transaction = transaction
				)
			}.let {
				DataSsmSessionLogListQueryResult(it)
			}
		}

	override fun dataSsmSessionLogGetQueryFunction(config: DataSsmConfig): DataSsmSessionLogGetQueryFunction =
		f2Function { cmd ->
			val logs = getSessionLogs(cmd.sessionId, config, cmd.bearerToken)
			val transaction = getTransaction(cmd.txId, config, cmd.bearerToken)
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

	private suspend fun getSessionLogs(
		session: DataSsmSessionId,
		config: DataSsmConfig,
		bearerToken: String?,
	): List<SsmSessionStateLog> {
		val query = SsmGetSessionLogsQuery(
			session = session,
			bearerToken = bearerToken
		)

		return try {
			SsmGetSessionLogsQueryFunctionImpl().ssmGetSessionLogsQueryFunction(config.chaincode).invoke(query).logs
		} catch (e: Exception) {
			e.printStackTrace()
			emptyList()
		}
	}

	private suspend fun getTransaction(
		id: TransactionId?,
		config: DataSsmConfig,
		bearerToken: String?,
	): Transaction? {
		return id?.let {
			val query = SsmGetTransactionQuery(
				id = id,
				bearerToken = bearerToken
			)
			SsmGetTransactionQueryFunctionImpl().ssmGetTransactionQueryFunction(config.chaincode).invoke(query).transaction
		}
	}

	private suspend fun SsmSessionStateDTO.toTxSession(
		ssmConfig: DataSsmConfig,
		bearerToken: String?,
	): DataSsmSession {
		val sessionLogs = getSessionLogs(session, ssmConfig, bearerToken)

		val firstTransactionId = sessionLogs.minByOrNull { sessionLog -> sessionLog.state.iteration }?.txId
		val lastTransactionId = sessionLogs.maxByOrNull { sessionLog -> sessionLog.state.iteration }?.txId

		val firstTransaction = getTransaction(firstTransactionId, ssmConfig, bearerToken)
		val lastTransaction = getTransaction(lastTransactionId, ssmConfig, bearerToken)

		return this.toTxSession(firstTransaction, lastTransaction)
	}

}
