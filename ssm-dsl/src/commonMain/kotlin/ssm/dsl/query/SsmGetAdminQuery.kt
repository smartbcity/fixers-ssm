package ssm.dsl.query

import f2.dsl.cqrs.Command
import f2.dsl.cqrs.Event
import f2.dsl.function.F2Function
import f2.dsl.function.F2FunctionRemote
import kotlinx.serialization.Serializable
import ssm.dsl.SsmAgent
import kotlin.js.JsExport
import kotlin.js.JsName

typealias SsmGetAdminFunction = F2Function<SsmGetAdminQuery, SsmGetAdminResult>
typealias SsmGetAdminRemoteFunction = F2FunctionRemote<SsmGetAdminQuery, SsmGetAdminResult>

@JsExport
@Serializable
@JsName("SsmGetAdminQuery")
class SsmGetAdminQuery(
	val baseUrl: String,
	val jwt: String?,
	val name: String
): Command

@JsExport
@Serializable
@JsName("SsmGetAdminResult")
class SsmGetAdminResult(
	val admin: SsmAgent?
): Event
