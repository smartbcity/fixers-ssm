package ssm.dsl.query

import f2.dsl.cqrs.Event
import f2.dsl.function.F2Function
import f2.dsl.function.F2FunctionRemote
import kotlinx.serialization.Serializable
import ssm.dsl.Ssm
import ssm.dsl.SsmCommand
import kotlin.js.JsExport
import kotlin.js.JsName

typealias SsmGetQueryFunction = F2Function<SsmGetQuery, SsmGetResult>
typealias SsmGetQueryRemoteFunction = F2FunctionRemote<SsmGetQuery, SsmGetResult>

@JsExport
@Serializable
@JsName("SsmGetQuery")
class SsmGetQuery(
		override val baseUrl: String,
		override val channelId: String?,
		override val chaincodeId: String?,
		override val bearerToken: String? = null,
		val name: String
): SsmCommand

@JsExport
@Serializable
@JsName("SsmGetResult")
class SsmGetResult(
		val ssm: Ssm?
): Event
