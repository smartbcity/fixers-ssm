package ssm.dsl.query

import f2.dsl.cqrs.Event
import f2.dsl.function.F2Function
import f2.dsl.function.F2FunctionRemote
import kotlinx.serialization.Serializable
import ssm.dsl.SsmCommand
import ssm.dsl.SsmSessionState
import kotlin.js.JsExport
import kotlin.js.JsName

typealias SsmGetSessionQueryFunction = F2Function<SsmGetSessionQuery, SsmGetSessionResult>
typealias SsmGetSessionQueryRemoteFunction = F2FunctionRemote<SsmGetSessionQuery, SsmGetSessionResult>

@JsExport
@Serializable
@JsName("SsmGetSessionQuery")
class SsmGetSessionQuery(
		override val baseUrl: String,
		override val channelId: String?,
		override val chaincodeId: String?,
		override val bearerToken: String? = null,
		val name: String
): SsmCommand

@JsExport
@Serializable
@JsName("SsmGetSessionResult")
class SsmGetSessionResult(
		val session: SsmSessionState?
): Event
