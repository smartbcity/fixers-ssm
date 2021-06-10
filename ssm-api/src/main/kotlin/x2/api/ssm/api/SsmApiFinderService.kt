package x2.api.ssm.api

import GetSsmListCommandBase
import GetSsmSessionCommandBase
import GetSsmSessionListCommandBase
import GetSsmSessionLogsCommandBase
import TxSsmBase
import TxSsmSessionBase
import TxSsmSessionStateBase
import f2.dsl.function.F2Function
import f2.function.spring.adapter.f2Function
import f2.function.spring.invokeSingle
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Service
import ssm.coucbdb.dsl.query.CdbGetSsmListQuery
import ssm.coucbdb.dsl.query.CdbGetSsmListQueryFunction
import ssm.coucbdb.dsl.query.CdbGetSsmSessionListQuery
import ssm.coucbdb.dsl.query.CdbGetSsmSessionListQueryFunction
import ssm.dsl.SsmCommand
import ssm.dsl.SsmSessionStateBase
import ssm.dsl.SsmSessionStateLog
import ssm.dsl.blockchain.TransactionBase
import ssm.dsl.blockchain.TransactionId
import ssm.dsl.query.SsmGetQueryFunction
import ssm.dsl.query.SsmGetSessionLogsQuery
import ssm.dsl.query.SsmGetSessionLogsQueryFunction
import ssm.dsl.query.SsmGetSessionQuery
import ssm.dsl.query.SsmGetSessionQueryFunction
import ssm.dsl.query.SsmGetTransactionQuery
import ssm.dsl.query.SsmGetTransactionQueryFunction
import x2.api.ssm.api.model.toSsm
import x2.api.ssm.api.model.toTxSession
import java.net.URLEncoder

@Service
class SsmApiFinderService(
	private val cdbGetSsmSessionListQueryFunction: CdbGetSsmSessionListQueryFunction,
	private val cdbGetSsmListQueryFunction: CdbGetSsmListQueryFunction,
	private val ssmGetQueryFunction: SsmGetQueryFunction,
	private val ssmGetSessionQueryFunction: SsmGetSessionQueryFunction,
	private val ssmGetSessionLogsQueryFunction: SsmGetSessionLogsQueryFunction,
	private val ssmGetTransactionQueryFunction: SsmGetTransactionQueryFunction,
) {

	@Bean
	fun getAllSsm(): F2Function<GetSsmListCommandBase, List<TxSsmBase>> = f2Function { cmd ->
		val command = CdbGetSsmListQuery(
			dbName = cmd.dbName
		)
		cdbGetSsmListQueryFunction.invokeSingle(command)
			.ssmList
			.map(ssm.dsl.Ssm::toSsm)
	}

	@Bean
	fun getSsm() = ssmGetQueryFunction

	@Bean
	fun getAllSessions(): F2Function<GetSsmSessionListCommandBase, List<TxSsmSessionBase>> = f2Function { cmd ->
		val command = CdbGetSsmSessionListQuery(
			dbName = cmd.dbName,
			ssm = cmd.ssm
		)
		cdbGetSsmSessionListQueryFunction.invokeSingle(command)
			.sessions
			.filter { sessionState -> sessionState.session.isNotBlank() }
			.map { sessionState -> sessionState.toSession(cmd)
			}
	}

	@Bean
	fun getSession(): F2Function<GetSsmSessionCommandBase, TxSsmSessionBase?> = f2Function { cmd ->
		val sessionQuery = SsmGetSessionQuery(
			name = cmd.sessionId,
			baseUrl = cmd.baseUrl,
			channelId = cmd.channelId,
			chaincodeId = cmd.chaincodeId,
			bearerToken = cmd.bearerToken
		)

		ssmGetSessionQueryFunction.invokeSingle(sessionQuery)
			.session
			?.toTxSession(cmd)
	}

	@Bean
	fun getSessionLogs(): F2Function<GetSsmSessionLogsCommandBase, List<TxSsmSessionStateBase>> = f2Function { cmd ->
		val logs = getSessionLogs(cmd.sessionId, cmd)

		logs.map { log ->
			val transaction = getTransaction(log.txId, cmd)
			TxSsmSessionStateBase(
				details = log.state,
				transaction = transaction
			)
		}
	}

	private suspend fun getSessionLogs(session: String, cmd: SsmCommand): List<SsmSessionStateLog> {
		val query = SsmGetSessionLogsQuery(
			session = session,
			baseUrl = cmd.baseUrl,
			channelId = cmd.channelId,
			chaincodeId = cmd.chaincodeId,
			bearerToken = cmd.bearerToken
		)
		return ssmGetSessionLogsQueryFunction.invokeSingle(query).logs
	}

	private suspend fun getTransaction(id: TransactionId?, cmd: SsmCommand): TransactionBase? {
		id ?: return null

		val query = SsmGetTransactionQuery(
			id = id,
			baseUrl = cmd.baseUrl,
			channelId = cmd.channelId,
			chaincodeId = cmd.chaincodeId,
			bearerToken = cmd.bearerToken
		)
		return ssmGetTransactionQueryFunction.invokeSingle(query).transaction
	}

	private suspend fun SsmSessionStateBase.toTxSession(cmd: SsmCommand): TxSsmSessionBase {
		val sanitizedSession = URLEncoder.encode(this.session, "utf-8")
		val sessionLogs = getSessionLogs(sanitizedSession, cmd)

		val firstTransactionId = sessionLogs.minByOrNull { sessionLog -> sessionLog.state.iteration }?.txId
		val lastTransactionId = sessionLogs.maxByOrNull { sessionLog -> sessionLog.state.iteration }?.txId

		val firstTransaction = getTransaction(firstTransactionId, cmd)
		val lastTransaction = getTransaction(lastTransactionId, cmd)

		return this.toTxSession(firstTransaction, lastTransaction)
	}
}
