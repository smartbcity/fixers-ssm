package ssm.dsl.query

import f2.dsl.cqrs.Command
import f2.dsl.cqrs.Event
import f2.dsl.function.F2Function
import f2.dsl.function.F2FunctionRemote
import kotlinx.serialization.Serializable
import ssm.dsl.Ssm
import kotlin.js.JsExport
import kotlin.js.JsName

typealias GetListSsmSessionQueryFunction = F2Function<GetListSsmSessionQuery, GetListSsmSessionResult>
typealias GetListSsmSessionRemoteQueryFunction = F2FunctionRemote<GetListSsmSessionQuery, GetListSsmSessionResult>

@JsExport
@Serializable
@JsName("GetListSsmSessionQuery")
class GetListSsmSessionQuery(
): Command


@JsExport
@Serializable
@JsName("GetListSsmSessionResult")
class GetListSsmSessionResult(
	val values: List<String>
): Event
