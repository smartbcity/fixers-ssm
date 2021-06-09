package x2.api.ssm.api

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
import ssm.dsl.query.SsmGetQueryFunction
import ssm.dsl.query.SsmGetSessionFirstAndLastTransactionQuery
import ssm.dsl.query.SsmGetSessionFirstAndLastTransactionQueryFunction
import ssm.dsl.query.SsmGetSessionLogsQuery
import ssm.dsl.query.SsmGetSessionLogsQueryFunction
import ssm.dsl.query.SsmGetSessionQuery
import ssm.dsl.query.SsmGetSessionQueryFunction
import x2.api.ssm.api.model.toSession
import x2.api.ssm.api.model.toSsm
import x2.api.ssm.domain.features.GetSsmListCommandBase
import x2.api.ssm.domain.features.GetSsmSessionCommandBase
import x2.api.ssm.domain.features.GetSsmSessionListCommandBase
import x2.api.ssm.domain.model.TxSsmBase
import x2.api.ssm.domain.model.TxSsmSessionBase
import java.net.URLEncoder

@Service
class SsmApiFinderService(
	private val cdbGetSsmSessionListQueryFunction: CdbGetSsmSessionListQueryFunction,
	private val cdbGetSsmListQueryFunction: CdbGetSsmListQueryFunction,
	private val ssmGetQueryFunction: SsmGetQueryFunction,
	private val ssmGetSessionQueryFunction: SsmGetSessionQueryFunction,
	private val ssmGetSessionFirstAndLastTransactionQueryFunction: SsmGetSessionFirstAndLastTransactionQueryFunction,
	private val ssmGetSessionLogsQueryFunction: SsmGetSessionLogsQueryFunction,
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
		val query = SsmGetSessionQuery(
			name = cmd.name,
			baseUrl = cmd.baseUrl,
			channelId = cmd.channelId,
			chaincodeId = cmd.chaincodeId,
			bearerToken = cmd.bearerToken
		)

		val sessionState = ssmGetSessionQueryFunction.invokeSingle(query).session
		sessionState?.toSession(cmd)
	}

	@Bean
	fun getSessionLogs(): F2Function<SsmGetSessionLogsQuery, List<SsmSessionStateLog>> = f2Function { cmd ->
		ssmGetSessionLogsQueryFunction.invokeSingle(cmd).logs
	}

	private suspend fun SsmSessionStateBase.toSession(cmd: SsmCommand): TxSsmSessionBase {
		val sanitizedSession = URLEncoder.encode(this.session, "utf-8")
		val query = SsmGetSessionFirstAndLastTransactionQuery(
			session = sanitizedSession,
			baseUrl = cmd.baseUrl,
			channelId = cmd.channelId,
			chaincodeId = cmd.chaincodeId,
			bearerToken = cmd.bearerToken
		)
		val result = ssmGetSessionFirstAndLastTransactionQueryFunction.invokeSingle(query)

		return this.toSession(result.firstTransaction, result.lastTransaction)
	}
}
