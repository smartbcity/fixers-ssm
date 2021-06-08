package ssm.dsl.query

import f2.dsl.cqrs.Event
import f2.dsl.function.F2Function
import f2.dsl.function.F2FunctionRemote
import kotlinx.serialization.Serializable
import ssm.dsl.SsmCommand
import kotlin.js.JsExport
import kotlin.js.JsName

typealias SsmListSsmQueryFunction = F2Function<SsmListSsmQuery, SsmListSsmResult>
typealias SsmListSsmQueryRemoteFunction = F2FunctionRemote<SsmListSsmQuery, SsmListSsmResult>

@JsExport
@Serializable
@JsName("SsmListSsmQuery")
class SsmListSsmQuery(
		override val baseUrl: String,
		override val channelId: String?,
		override val chaincodeId: String?,
		override val bearerToken: String? = null,
): SsmCommand


@JsExport
@Serializable
@JsName("SsmListSsmResult")
class SsmListSsmResult(
		val values: Array<String>
): Event
