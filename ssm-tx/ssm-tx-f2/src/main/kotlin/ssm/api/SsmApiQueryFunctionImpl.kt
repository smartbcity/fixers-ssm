package ssm.api

import f2.dsl.fnc.f2Function
import f2.dsl.fnc.invoke
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.toList
import ssm.api.model.toTxSession
import ssm.api.model.toTxSsm
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
import ssm.tx.dsl.SsmApiQueryFunctions
import ssm.tx.dsl.config.SsmTxConfig
import ssm.tx.dsl.features.query.TxSsmGetQueryFunction
import ssm.tx.dsl.features.query.TxSsmGetQueryResult
import ssm.tx.dsl.features.query.TxSsmListQueryFunction
import ssm.tx.dsl.features.query.TxSsmListQueryResult
import ssm.tx.dsl.features.query.TxSsmSessionGetQueryFunction
import ssm.tx.dsl.features.query.TxSsmSessionGetQueryResult
import ssm.tx.dsl.features.query.TxSsmSessionListQueryFunction
import ssm.tx.dsl.features.query.TxSsmSessionListQueryResult
import ssm.tx.dsl.features.query.TxSsmSessionLogGetQueryFunction
import ssm.tx.dsl.features.query.TxSsmSessionLogGetQueryResult
import ssm.tx.dsl.features.query.TxSsmSessionLogListQueryFunction
import ssm.tx.dsl.features.query.TxSsmSessionLogListQueryResult
import ssm.tx.dsl.model.TxSsmSession
import ssm.tx.dsl.model.TxSsmSessionId
import ssm.tx.dsl.model.TxSsmSessionState

class SsmApiQueryFunctionImpl: SsmApiQueryFunctions {

	override fun txSsmListQueryFunction(config: SsmTxConfig): TxSsmListQueryFunction = f2Function { query ->
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
				result.toTxSsm()
			}.let {
				val txSsmListQueryResult = TxSsmListQueryResult(it.toList())
				txSsmListQueryResult
			}
	}

	override fun txSsmGetQueryFunction(config: SsmTxConfig): TxSsmGetQueryFunction = f2Function { cmd ->
		val command = SsmGetQuery(
			name = cmd.ssm,
			bearerToken = cmd.bearerToken
		)

		SsmGetQueryFunctionImpl().ssmGetQueryFunction(config.chaincode).invoke(command)
			.ssm
			?.toTxSsm()
			.let(::TxSsmGetQueryResult)
	}

	override fun txSsmSessionListQueryFunction(config: SsmTxConfig): TxSsmSessionListQueryFunction = f2Function { query ->
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
				TxSsmSessionListQueryResult(it)
			}
	}

	override fun txSsmSessionGetQueryFunction(config: SsmTxConfig): TxSsmSessionGetQueryFunction = f2Function { cmd ->
		val sessionQuery = SsmGetSessionQuery(
			name = cmd.sessionId,
			bearerToken = cmd.bearerToken
		)

		try {
			SsmGetSessionQueryFunctionImpl().ssmGetSessionQueryFunction(config.chaincode).invoke(sessionQuery)
				.session
				?.toTxSession(config, cmd.bearerToken).let {
					TxSsmSessionGetQueryResult(
						it
					)
				}
		} catch (e: Exception) {
			e.printStackTrace()
			TxSsmSessionGetQueryResult(null)
		}
	}

//	private fun getConfig(cmd: TxQueryDTO): SsmTxConfig {
//		return ssmConfigProperties.list[cmd.ssm]?.entries?.first()?.value
//			?: throw IllegalArgumentException("Configuration of SSM [${cmd.ssm}] not found")
//	}

	override fun txSsmSessionLogListQueryFunction(config: SsmTxConfig): TxSsmSessionLogListQueryFunction =
		f2Function { cmd ->
			val logs = getSessionLogs(cmd.sessionId, config, cmd.bearerToken)

			logs.map { log ->
				val transaction = getTransaction(log.txId, config, cmd.bearerToken)
				TxSsmSessionState(
					details = log.state,
					transaction = transaction
				)
			}.let {
				TxSsmSessionLogListQueryResult(it)
			}
		}

	override fun txSsmSessionLogGetQueryFunction(config: SsmTxConfig): TxSsmSessionLogGetQueryFunction =
		f2Function { cmd ->
			val logs = getSessionLogs(cmd.sessionId, config, cmd.bearerToken)
			val transaction = getTransaction(cmd.txId, config, cmd.bearerToken)
			logs.firstOrNull { log -> log.txId == cmd.txId }
				?.let { sessionState ->
					TxSsmSessionState(
						details = sessionState.state,
						transaction = transaction
					)
				}.let {
					TxSsmSessionLogGetQueryResult(it)
				}
		}

	private suspend fun getSessionLogs(
		session: TxSsmSessionId,
		config: SsmTxConfig,
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
		config: SsmTxConfig,
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
		ssmConfig: SsmTxConfig,
		bearerToken: String?,
	): TxSsmSession {
		val sessionLogs = getSessionLogs(session, ssmConfig, bearerToken)

		val firstTransactionId = sessionLogs.minByOrNull { sessionLog -> sessionLog.state.iteration }?.txId
		val lastTransactionId = sessionLogs.maxByOrNull { sessionLog -> sessionLog.state.iteration }?.txId

		val firstTransaction = getTransaction(firstTransactionId, ssmConfig, bearerToken)
		val lastTransaction = getTransaction(lastTransactionId, ssmConfig, bearerToken)

		return this.toTxSession(firstTransaction, lastTransaction)
	}

}
