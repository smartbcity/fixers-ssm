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

typealias GetSsmAdminFunction = F2Function<GetSsmAdminQuery, GetSsmAdminResult>
typealias GetSsmAdminRemoteFunction = F2FunctionRemote<GetSsmAdminQuery, GetSsmAdminResult>

@JsExport
@Serializable
@JsName("GetSsmAdminQuery")
class GetSsmAdminQuery(
	val name: String
): Command

@JsExport
@Serializable
@JsName("GetSsmAdminResult")
class GetSsmAdminResult(
	val admin: SsmAgent?
): Event
