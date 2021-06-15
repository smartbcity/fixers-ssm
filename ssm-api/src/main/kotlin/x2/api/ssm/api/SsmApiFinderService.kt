package x2.api.ssm.api

import GetSsmListCommandBase
import GetSsmSessionCommandBase
import GetSsmSessionListCommandBase
import GetSsmSessionLogCommandBase
import GetSsmSessionLogListCommandBase
import TxSsmBase
import TxSsmSessionBase
import TxSsmSessionId
import TxSsmSessionStateBase
import f2.dsl.function.F2Function
import f2.function.spring.adapter.f2Function
import f2.function.spring.invokeSingle
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.toList
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Service
import ssm.chaincode.dsl.SsmBase
import ssm.chaincode.dsl.SsmSessionStateBase
import ssm.chaincode.dsl.SsmSessionStateLog
import ssm.chaincode.dsl.blockchain.TransactionBase
import ssm.chaincode.dsl.blockchain.TransactionId
import ssm.chaincode.dsl.query.SsmGetQueryFunction
import ssm.chaincode.dsl.query.SsmGetSessionLogsQuery
import ssm.chaincode.dsl.query.SsmGetSessionLogsQueryFunction
import ssm.chaincode.dsl.query.SsmGetSessionQuery
import ssm.chaincode.dsl.query.SsmGetSessionQueryFunction
import ssm.chaincode.dsl.query.SsmGetTransactionQuery
import ssm.chaincode.dsl.query.SsmGetTransactionQueryFunction
import ssm.couchdb.dsl.query.CdbGetSsmListQuery
import ssm.couchdb.dsl.query.CdbGetSsmListQueryFunction
import ssm.couchdb.dsl.query.CdbGetSsmListQueryResult
import ssm.couchdb.dsl.query.CdbGetSsmSessionListQuery
import ssm.couchdb.dsl.query.CdbGetSsmSessionListQueryFunction
import x2.api.config.SsmLocationProperties
import x2.api.config.X2SsmConfig
import x2.api.ssm.api.model.toTxSession
import x2.api.ssm.api.model.toTxSsm
import x2.api.ssm.api.utils.x2F2Function

@Service
class SsmApiFinderService(
	private val cdbGetSsmSessionListQueryFunction: CdbGetSsmSessionListQueryFunction,
	private val cdbGetSsmListQueryFunction: CdbGetSsmListQueryFunction,
	private val ssmGetQueryFunction: SsmGetQueryFunction,
	private val ssmGetSessionQueryFunction: SsmGetSessionQueryFunction,
	private val ssmGetSessionLogsQueryFunction: SsmGetSessionLogsQueryFunction,
	private val ssmGetTransactionQueryFunction: SsmGetTransactionQueryFunction,
	private val x2SsmConfig: X2SsmConfig
) {

	@Bean
	fun getAllSsm(): F2Function<GetSsmListCommandBase, List<TxSsmBase>> = f2Function {
		val commands = x2SsmConfig.ssmMap().flatMap { (ssmName, ssmConfigs) ->
			ssmConfigs.map { (ssmVersion, ssmConfig) ->
				CdbGetSsmListQuery(
					dbConfig = ssmConfig.couchdb,
					dbName = ssmConfig.dbName
				)
			}
		}.asFlow()

		cdbGetSsmListQueryFunction(commands).toList()
			.flatMap(CdbGetSsmListQueryResult::ssmList)
			.map(SsmBase::toTxSsm)
	}

	@Bean
	fun getSsm() = ssmGetQueryFunction

	@Bean
	fun getAllSessions(): F2Function<GetSsmSessionListCommandBase, List<TxSsmSessionBase>> = x2F2Function { cmd, ssmConfig ->
		val config = ssmConfig.entries.first().value
		val command = CdbGetSsmSessionListQuery(
			dbConfig = config.couchdb,
			dbName = config.dbName,
			ssm = cmd.ssm
		)
		cdbGetSsmSessionListQueryFunction.invokeSingle(command)
			.sessions
			.filter { sessionState -> sessionState.session.isNotBlank() }
			.map { sessionState -> sessionState.toTxSession(config, cmd.bearerToken) }
	}

	@Bean
	fun getSession(): F2Function<GetSsmSessionCommandBase, TxSsmSessionBase?> = x2F2Function { cmd, ssmConfig ->
		val config = ssmConfig.entries.first().value
		val sessionQuery = SsmGetSessionQuery(
			name = cmd.sessionId,
			baseUrl = config.baseUrl,
			channelId = config.channel,
			chaincodeId = config.chaincode,
			bearerToken = cmd.bearerToken
		)

		try {
			ssmGetSessionQueryFunction.invokeSingle(sessionQuery)
				.session
				?.toTxSession(config, cmd.bearerToken)
		} catch (e: Exception) {
			e.printStackTrace()
			null
		}
	}

	@Bean
	fun getSessionLogs(): F2Function<GetSsmSessionLogListCommandBase, List<TxSsmSessionStateBase>> = x2F2Function { cmd, ssmConfig ->
		val config = ssmConfig.entries.first().value
		val logs = getSessionLogs(cmd.sessionId, config, cmd.bearerToken)

		logs.map { log ->
			val transaction = getTransaction(log.txId, config, cmd.bearerToken)
			TxSsmSessionStateBase(
				details = log.state,
				transaction = transaction
			)
		}
	}

	@Bean
	fun getOneSessionLog(): F2Function<GetSsmSessionLogCommandBase, TxSsmSessionStateBase?> = x2F2Function { cmd, ssmConfig ->
		val config = ssmConfig.entries.first().value
		val logs = getSessionLogs(cmd.sessionId, config, cmd.bearerToken)

		val state = logs.firstOrNull { log -> log.txId == cmd.txId }
			?.state
			?: return@x2F2Function null

		val transaction = getTransaction(cmd.txId, config, cmd.bearerToken)

		TxSsmSessionStateBase(
			details = state,
			transaction = transaction
		)
	}

	private suspend fun getSessionLogs(session: TxSsmSessionId, ssmConfig: SsmLocationProperties, bearerToken: String?): List<SsmSessionStateLog> {
		val query = SsmGetSessionLogsQuery(
			session = session,
			baseUrl = ssmConfig.baseUrl,
			channelId = ssmConfig.channel,
			chaincodeId = ssmConfig.chaincode,
			bearerToken = bearerToken
		)

		return try {
			ssmGetSessionLogsQueryFunction.invokeSingle(query).logs
		} catch (e: Exception) {
			e.printStackTrace()
			emptyList()
		}
	}

	private suspend fun getTransaction(id: TransactionId?, ssmConfig: SsmLocationProperties, bearerToken: String?): TransactionBase? {
		id ?: return null

		val query = SsmGetTransactionQuery(
			id = id,
			baseUrl = ssmConfig.baseUrl,
			channelId = ssmConfig.channel,
			chaincodeId = ssmConfig.chaincode,
			bearerToken = bearerToken
		)
		return ssmGetTransactionQueryFunction.invokeSingle(query).transaction
	}

	private suspend fun SsmSessionStateBase.toTxSession(ssmConfig: SsmLocationProperties, bearerToken: String?): TxSsmSessionBase {
		val sessionLogs = getSessionLogs(session, ssmConfig, bearerToken)

		val firstTransactionId = sessionLogs.minByOrNull { sessionLog -> sessionLog.state.iteration }?.txId
		val lastTransactionId = sessionLogs.maxByOrNull { sessionLog -> sessionLog.state.iteration }?.txId

		val firstTransaction = getTransaction(firstTransactionId, ssmConfig, bearerToken)
		val lastTransaction = getTransaction(lastTransactionId, ssmConfig, bearerToken)

		return this.toTxSession(firstTransaction, lastTransaction)
	}
}
