package ssm.dsl.query

import f2.dsl.cqrs.Event
import f2.dsl.function.F2Function
import f2.dsl.function.F2FunctionRemote
import kotlinx.serialization.Serializable
import ssm.dsl.SsmCommand
import ssm.dsl.SsmAgentBase
import kotlin.js.JsExport
import kotlin.js.JsName

typealias SsmGetAdminFunction = F2Function<SsmGetAdminQuery, SsmGetAdminResult>
typealias SsmGetAdminRemoteFunction = F2FunctionRemote<SsmGetAdminQuery, SsmGetAdminResult>

@JsExport
@Serializable
@JsName("SsmGetAdminQuery")
class SsmGetAdminQuery(
		override val baseUrl: String,
		override val channelId: String?,
		override val chaincodeId: String?,
		override val bearerToken: String? = null,
		val name: String
): SsmCommand

@JsExport
@Serializable
@JsName("SsmGetAdminResult")
class SsmGetAdminResult(
	val admin: SsmAgentBase?
): Event
