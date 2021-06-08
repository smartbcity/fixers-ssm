package ssm.dsl.query

import f2.dsl.cqrs.Command
import f2.dsl.cqrs.Event
import f2.dsl.function.F2Function
import f2.dsl.function.F2FunctionRemote
import kotlinx.serialization.Serializable
import kotlin.js.JsExport
import kotlin.js.JsName

typealias SsmListSessionQueryFunction = F2Function<SsmListSessionQuery, SsmListSessionResult>
typealias SsmListSessionRemoteQueryFunction = F2FunctionRemote<SsmListSessionQuery, SsmListSessionResult>

@JsExport
@Serializable
@JsName("SsmListSessionQuery")
class SsmListSessionQuery(
	val baseUrl: String,
	val jwt: String?,
): Command


@JsExport
@Serializable
@JsName("SsmListSessionResult")
class SsmListSessionResult(
	val values: Array<String>
): Event
