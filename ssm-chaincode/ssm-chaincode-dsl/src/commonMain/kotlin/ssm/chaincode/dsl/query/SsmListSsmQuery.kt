package ssm.chaincode.dsl.query

import f2.dsl.cqrs.Event
import f2.dsl.fnc.F2Function
import kotlinx.serialization.Serializable
import ssm.chaincode.dsl.SsmCommandDTO
import kotlin.js.JsExport
import kotlin.js.JsName

typealias SsmListSsmQueryFunction = F2Function<SsmListSsmQuery, SsmListSsmResult>

@Serializable
@JsExport
@JsName("SsmListSsmQuery")
class SsmListSsmQuery(
		override val baseUrl: String,
		override val channelId: String?,
		override val chaincodeId: String?,
		override val bearerToken: String? = null,
): SsmCommandDTO

@Serializable
@JsExport
@JsName("SsmListSsmResult")
class SsmListSsmResult(
		val values: Array<String>
): Event
