package ssm.dsl.query

import f2.dsl.cqrs.Command
import f2.dsl.cqrs.Event
import f2.dsl.function.F2Function
import f2.dsl.function.F2FunctionRemote
import ssm.dsl.Ssm

typealias GetSsmFunction = F2Function<GetSsmQuery, GetSsmResult>
typealias GetSsmRemoteFunction = F2FunctionRemote<GetSsmQuery, GetSsmResult>

class GetSsmQuery(
	val name: String
): Command

class GetSsmResult(
	val ssm: Ssm?
): Event
