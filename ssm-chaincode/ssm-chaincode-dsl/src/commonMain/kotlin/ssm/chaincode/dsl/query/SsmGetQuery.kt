package ssm.chaincode.dsl.query

import f2.dsl.cqrs.Event
import f2.dsl.fnc.F2Function
import kotlinx.serialization.Serializable
import ssm.chaincode.dsl.Ssm
import ssm.chaincode.dsl.SsmCommandDTO
import kotlin.js.JsExport
import kotlin.js.JsName

typealias SsmGetQueryFunction = F2Function<SsmGetQuery, SsmGetResult>

@JsExport
@Serializable
@JsName("SsmGetQuery")
class SsmGetQuery(
		override val baseUrl: String,
		override val channelId: String?,
		override val chaincodeId: String?,
		override val bearerToken: String? = null,
		val name: String
): SsmCommandDTO

@JsExport
@Serializable
@JsName("SsmGetResult")
class SsmGetResult(
	val ssmBase: Ssm?
): Event
