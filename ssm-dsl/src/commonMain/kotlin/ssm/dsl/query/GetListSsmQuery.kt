package ssm.dsl.query

import f2.dsl.cqrs.Command
import f2.dsl.cqrs.Event
import f2.dsl.function.F2Function
import f2.dsl.function.F2FunctionRemote
import kotlinx.serialization.Serializable
import ssm.dsl.Ssm
import kotlin.js.JsExport
import kotlin.js.JsName

typealias GetListSsmQueryFunction = F2Function<GetListSsmQuery, GetListSsmResult>
typealias GetListSsmQueryRemoteFunction = F2FunctionRemote<GetListSsmQuery, GetListSsmResult>

@JsExport
@Serializable
@JsName("GetListSsmQuery")
class GetListSsmQuery(
): Command


@JsExport
@Serializable
@JsName("GetListSsmResult")
class GetListSsmResult(
	val values: List<String>
): Event
