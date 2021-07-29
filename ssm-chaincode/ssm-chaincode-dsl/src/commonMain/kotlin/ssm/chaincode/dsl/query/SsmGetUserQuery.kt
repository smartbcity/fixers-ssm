package ssm.chaincode.dsl.query

import f2.dsl.cqrs.Event
import f2.dsl.fnc.F2Function
import kotlinx.serialization.Serializable
import ssm.chaincode.dsl.SsmAgent
import ssm.chaincode.dsl.SsmCommandDTO
import kotlin.js.JsExport
import kotlin.js.JsName

typealias SsmGetUserFunction = F2Function<SsmGetUserQuery, SsmGetUserResult>

@Serializable
@JsExport
@JsName("SsmGetUserQuery")
class SsmGetUserQuery(
		override val baseUrl: String,
		override val channelId: String?,
		override val chaincodeId: String?,
		override val bearerToken: String? = null,
		val name: String
): SsmCommandDTO

@Serializable
@JsExport
@JsName("SsmGetUserResult")
class SsmGetUserResult(
	val user: SsmAgent?
): Event
