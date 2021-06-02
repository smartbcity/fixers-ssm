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
import ssm.dsl.SsmSessionStateLog
import ssm.dsl.query.SsmGetQueryFunction
import ssm.dsl.query.SsmGetSessionFirstAndLastTransactionQuery
import ssm.dsl.query.SsmGetSessionFirstAndLastTransactionQueryFunction
import ssm.dsl.query.SsmGetSessionLogsQuery
import ssm.dsl.query.SsmGetSessionLogsQueryFunction
import ssm.dsl.query.SsmGetSessionQueryFunction
import x2.api.ssm.api.model.toSession
import x2.api.ssm.api.model.toSsm
import x2.api.ssm.model.SsmBase
import x2.api.ssm.model.SsmSessionBase
import x2.api.ssm.model.features.GetSsmListCommandBase
import x2.api.ssm.model.features.GetSsmSessionListCommandBase
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
	fun getAllSsm(): F2Function<GetSsmListCommandBase, List<SsmBase>> = f2Function { cmd ->
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
	fun getAllSessions(): F2Function<GetSsmSessionListCommandBase, List<SsmSessionBase>> = f2Function { cmd ->
		val command = CdbGetSsmSessionListQuery(
			dbName = cmd.dbName,
			ssm = cmd.ssm
		)
		cdbGetSsmSessionListQueryFunction.invokeSingle(command)
			.sessions
			.filter { sessionState -> sessionState.session.isNotBlank() }
			.map { sessionState ->
				val sanitizedSession = URLEncoder.encode(sessionState.session, "utf-8")
				val query = SsmGetSessionFirstAndLastTransactionQuery(
					session = sanitizedSession,
					baseUrl = cmd.baseUrl,
					channelId = cmd.channelId,
					chaincodeId = cmd.chaincodeId,
					bearerToken = cmd.bearerToken
				)
				val result = ssmGetSessionFirstAndLastTransactionQueryFunction.invokeSingle(query)

				sessionState.toSession(result.firstTransaction, result.lastTransaction)
			}
	}

	@Bean
	fun getSession() = ssmGetSessionQueryFunction
}
