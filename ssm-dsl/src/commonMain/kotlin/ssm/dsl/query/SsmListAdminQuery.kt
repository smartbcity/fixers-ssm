package ssm.dsl.query

import f2.dsl.cqrs.Command
import f2.dsl.cqrs.Event
import f2.dsl.function.F2Function
import f2.dsl.function.F2FunctionRemote
import kotlinx.serialization.Serializable
import kotlin.js.JsExport
import kotlin.js.JsName

typealias SsmListAdminQueryFunction = F2Function<SsmListAdminQuery, SsmListAdminResult>
typealias SsmListAdminQueryRemoteFunction = F2FunctionRemote<SsmListAdminQuery, SsmListAdminResult>

@JsExport
@Serializable
@JsName("SsmListAdminQuery")
class SsmListAdminQuery(
	val baseUrl: String,
	val jwt: String? = null,
): Command

@JsExport
@Serializable
@JsName("SsmListAdminResult")
class SsmListAdminResult(
	val values: List<String>
): Event
