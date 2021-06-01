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
import ssm.dsl.SsmSessionState
import ssm.dsl.query.SsmGetQueryFunction
import ssm.dsl.query.SsmGetSessionLogQueryFunction
import ssm.dsl.query.SsmGetSessionQueryFunction
import ssm.dsl.query.SsmListSessionQueryFunction
import ssm.dsl.query.SsmListSsmQueryFunction
import x2.api.ssm.api.model.toSession
import x2.api.ssm.api.model.toSsm
import x2.api.ssm.model.SsmBase
import x2.api.ssm.model.SsmSessionBase
import x2.api.ssm.model.features.GetSsmListCommandBase
import x2.api.ssm.model.features.GetSsmSessionListCommandBase

@Service
class SsmApiFinderService(
	private val cdbGetSsmSessionListQueryFunction: CdbGetSsmSessionListQueryFunction,
	private val cdbGetSsmListQueryFunction: CdbGetSsmListQueryFunction,
	private val ssmGetQueryFunction: SsmGetQueryFunction,
	private val ssmGetSessionQueryFunction: SsmGetSessionQueryFunction,
	private val ssmListSsmQueryFunction: SsmListSsmQueryFunction,
	private val ssmListSessionQueryFunction: SsmListSessionQueryFunction
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
	fun getAllSsmIds() = ssmListSsmQueryFunction

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
			.map(SsmSessionState::toSession)
	}

	@Bean
	fun getAllSessionIds() = ssmListSessionQueryFunction

	@Bean
	fun getSession() = ssmGetSessionQueryFunction
}
