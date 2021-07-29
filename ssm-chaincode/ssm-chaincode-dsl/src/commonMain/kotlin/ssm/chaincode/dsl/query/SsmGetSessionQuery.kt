package ssm.chaincode.dsl.query

import f2.dsl.cqrs.Event
import f2.dsl.fnc.F2Function
import kotlinx.serialization.Serializable
import ssm.chaincode.dsl.SsmCommandDTO
import ssm.chaincode.dsl.SsmSessionStateBase
import kotlin.js.JsExport
import kotlin.js.JsName

typealias SsmGetSessionQueryFunction = F2Function<SsmGetSessionQuery, SsmGetSessionResult>

@Serializable
@JsExport
@JsName("SsmGetSessionQuery")
class SsmGetSessionQuery(
		override val baseUrl: String,
		override val channelId: String?,
		override val chaincodeId: String?,
		override val bearerToken: String? = null,
		val name: String
): SsmCommandDTO

@Serializable
@JsExport
@JsName("SsmGetSessionResult")
class SsmGetSessionResult(
	val session: SsmSessionStateBase?
): Event
