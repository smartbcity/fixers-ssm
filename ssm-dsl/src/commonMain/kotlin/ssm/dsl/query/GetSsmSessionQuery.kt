package ssm.dsl.query

import f2.dsl.cqrs.Command
import f2.dsl.cqrs.Event
import f2.dsl.function.F2Function
import f2.dsl.function.F2FunctionRemote
import kotlinx.serialization.Serializable
import ssm.dsl.SsmSessionState
import kotlin.js.JsExport
import kotlin.js.JsName

typealias GetSsmSessionFunction = F2Function<GetSsmSessionQuery, GetSsmSessionResult>
typealias GetSsmSessionRemoteFunction = F2FunctionRemote<GetSsmSessionQuery, GetSsmSessionResult>

@JsExport
@Serializable
@JsName("GetSsmSessionQuery")
class GetSsmSessionQuery(
	val baseUrl: String,
	val jwt: String?,
	val name: String
): Command

@JsExport
@Serializable
@JsName("GetSsmSessionResult")
class GetSsmSessionResult(
	val session: SsmSessionState?
): Event
