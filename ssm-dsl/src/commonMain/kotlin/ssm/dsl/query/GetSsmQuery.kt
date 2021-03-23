package ssm.dsl.query

import f2.dsl.cqrs.Command
import f2.dsl.cqrs.Event
import f2.dsl.function.F2Function
import f2.dsl.function.F2FunctionRemote
import kotlinx.serialization.Serializable
import ssm.dsl.Ssm
import kotlin.js.JsExport
import kotlin.js.JsName

typealias GetSsmQueryFunction = F2Function<GetSsmQuery, GetSsmResult>
typealias GetSsmRemoteFunction = F2FunctionRemote<GetSsmQuery, GetSsmResult>

@JsExport
@Serializable
@JsName("GetSsmQuery")
class GetSsmQuery(
	val baseUrl: String,
	val jwt: String?,
	val name: String
): Command

@JsExport
@Serializable
@JsName("GetSsmResult")
class GetSsmResult(
	val ssm: Ssm?
): Event
