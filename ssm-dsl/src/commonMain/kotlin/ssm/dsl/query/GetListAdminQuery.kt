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

typealias GetListAdminQueryFunction = F2Function<GetListAdminQuery, GetListAdminResult>
typealias GetListAdminQueryRemoteFunction = F2FunctionRemote<GetListAdminQuery, GetListAdminResult>

@JsExport
@Serializable
@JsName("GetListAdminQuery")
class GetListAdminQuery(
): Command

@JsExport
@Serializable
@JsName("GetListAdminResult")
class GetListAdminResult(
	val values: List<String>
): Event
