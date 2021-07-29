package ssm.chaincode.dsl.query

import f2.dsl.cqrs.Event
import f2.dsl.fnc.F2Function
import kotlinx.serialization.Serializable
import ssm.chaincode.dsl.SsmCommandDTO
import kotlin.js.JsExport
import kotlin.js.JsName

typealias SsmListSessionQueryFunction = F2Function<SsmListSessionQuery, SsmListSessionResult>

@Serializable
@JsExport
@JsName("SsmListSessionQuery")
class SsmListSessionQuery(
		override val baseUrl: String,
		override val channelId: String?,
		override val chaincodeId: String?,
		override val bearerToken: String? = null,
): SsmCommandDTO

@Serializable
@JsExport
@JsName("SsmListSessionResult")
class SsmListSessionResult(
		val values: Array<String>
): Event
