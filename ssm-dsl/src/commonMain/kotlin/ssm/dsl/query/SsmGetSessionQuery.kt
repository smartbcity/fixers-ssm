package ssm.dsl.query

import f2.dsl.cqrs.Command
import f2.dsl.cqrs.Event
import f2.dsl.function.F2Function
import f2.dsl.function.F2FunctionRemote
import kotlinx.serialization.Serializable
import ssm.dsl.SsmSessionStateBase
import kotlin.js.JsExport
import kotlin.js.JsName

typealias SsmGetSessionFunction = F2Function<SsmGetSessionQuery, SsmGetSessionResult>
typealias SsmGetSessionRemoteFunction = F2FunctionRemote<SsmGetSessionQuery, SsmGetSessionResult>

@JsExport
@Serializable
@JsName("SsmGetSessionQuery")
class SsmGetSessionQuery(
	val baseUrl: String,
	val jwt: String?,
	val name: String
): Command

@JsExport
@Serializable
@JsName("SsmGetSessionResult")
class SsmGetSessionResult(
	val session: SsmSessionStateBase?
): Event
