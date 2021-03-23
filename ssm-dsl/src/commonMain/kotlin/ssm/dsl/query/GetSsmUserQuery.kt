package ssm.dsl.query

import f2.dsl.cqrs.Command
import f2.dsl.cqrs.Event
import f2.dsl.function.F2Function
import f2.dsl.function.F2FunctionRemote
import kotlinx.serialization.Serializable
import ssm.dsl.Ssm
import ssm.dsl.SsmAgent
import kotlin.js.JsExport
import kotlin.js.JsName

typealias GetSsmUserFunction = F2Function<GetSsmUserQuery, GetSsmUserResult>
typealias GetSsmUserRemoteFunction = F2FunctionRemote<GetSsmUserQuery, GetSsmUserResult>

@JsExport
@Serializable
@JsName("GetSsmUserQuery")
class GetSsmUserQuery(
	val baseUrl: String,
	val jwt: String?,
	val name: String
): Command

@JsExport
@Serializable
@JsName("GetSsmUserResult")
class GetSsmUserResult(
	val user: SsmAgent?
): Event
