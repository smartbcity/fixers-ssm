package ssm.dsl.query

import f2.dsl.cqrs.Event
import f2.dsl.function.F2Function
import f2.dsl.function.F2FunctionRemote
import kotlinx.serialization.Serializable
import ssm.dsl.SsmCommand
import kotlin.js.JsExport
import kotlin.js.JsName

typealias SsmListAdminQueryFunction = F2Function<SsmListAdminQuery, SsmListAdminResult>
typealias SsmListAdminQueryRemoteFunction = F2FunctionRemote<SsmListAdminQuery, SsmListAdminResult>

@JsExport
@Serializable
@JsName("SsmListAdminQuery")
class SsmListAdminQuery(
		override val baseUrl: String,
		override val channelId: String?,
		override val chaincodeId: String?,
		override val bearerToken: String? = null,
): SsmCommand

@JsExport
@Serializable
@JsName("SsmListAdminResult")
class SsmListAdminResult(
	val values: List<String>
): Event
