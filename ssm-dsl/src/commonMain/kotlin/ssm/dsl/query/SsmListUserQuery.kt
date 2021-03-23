package ssm.dsl.query

import f2.dsl.cqrs.Command
import f2.dsl.cqrs.Event
import f2.dsl.function.F2Function
import f2.dsl.function.F2FunctionRemote
import kotlinx.serialization.Serializable
import kotlin.js.JsExport
import kotlin.js.JsName

typealias SsmListUserQueryFunction = F2Function<SsmListUserQuery, SsmListUserResult>
typealias SsmListUserQueryRemoteFunction = F2FunctionRemote<SsmListUserQuery, SsmListUserResult>

@JsExport
@Serializable
@JsName("SsmListUserQuery")
class SsmListUserQuery(
	val baseUrl: String,
	val jwt: String?,
): Command

@JsExport
@Serializable
@JsName("SsmListUserResult")
class SsmListUserResult(
	val values: List<String>
): Event
