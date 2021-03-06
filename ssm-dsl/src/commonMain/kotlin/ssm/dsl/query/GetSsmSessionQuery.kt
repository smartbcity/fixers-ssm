package ssm.dsl.query

import f2.dsl.cqrs.Command
import f2.dsl.cqrs.Event
import f2.dsl.function.F2Function
import f2.dsl.function.F2FunctionRemote
import ssm.dsl.SessionState

typealias GetSsmSessionFunction = F2Function<GetSsmSessionQuery, GetSsmSessionResult>
typealias GetSsmSessionRemoteFunction = F2FunctionRemote<GetSsmSessionQuery, GetSsmSessionResult>

class GetSsmSessionQuery(
	val name: String
): Command

class GetSsmSessionResult(
	val session: SessionState?
): Event
