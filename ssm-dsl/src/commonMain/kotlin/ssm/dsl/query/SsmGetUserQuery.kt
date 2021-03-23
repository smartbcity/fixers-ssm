package ssm.dsl.query

import f2.dsl.cqrs.Command
import f2.dsl.cqrs.Event
import f2.dsl.function.F2Function
import f2.dsl.function.F2FunctionRemote
import kotlinx.serialization.Serializable
import ssm.dsl.SsmAgent
import kotlin.js.JsExport
import kotlin.js.JsName

typealias SsmGetUserFunction = F2Function<SsmGetUserQuery, SsmGetUserResult>
typealias SsmGetUserRemoteFunction = F2FunctionRemote<SsmGetUserQuery, SsmGetUserResult>

@JsExport
@Serializable
@JsName("SsmGetUserQuery")
class SsmGetUserQuery(
	val baseUrl: String,
	val jwt: String?,
	val name: String
): Command

@JsExport
@Serializable
@JsName("SsmGetUserResult")
class SsmGetUserResult(
	val user: SsmAgent?
): Event
