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

typealias GetListUserQueryFunction = F2Function<GetListUserQuery, GetListUserResult>
typealias GetListUserQueryRemoteFunction = F2FunctionRemote<GetListUserQuery, GetListUserResult>

@JsExport
@Serializable
@JsName("GetListUserQuery")
class GetListUserQuery(
): Command

@JsExport
@Serializable
@JsName("GetListUserResult")
class GetListUserResult(
	val values: List<String>
): Event
