package ssm.chaincode.dsl.query

import f2.dsl.cqrs.Event
import f2.dsl.function.F2Function
import f2.dsl.function.F2FunctionRemote
import kotlinx.serialization.Serializable
import ssm.chaincode.dsl.SsmAgentBase
import ssm.chaincode.dsl.SsmCommand
import kotlin.js.JsExport
import kotlin.js.JsName

typealias SsmGetUserFunction = F2Function<SsmGetUserQuery, SsmGetUserResult>
typealias SsmGetUserRemoteFunction = F2FunctionRemote<SsmGetUserQuery, SsmGetUserResult>

@JsExport
@Serializable
@JsName("SsmGetUserQuery")
class SsmGetUserQuery(
		override val baseUrl: String,
		override val channelId: String?,
		override val chaincodeId: String?,
		override val bearerToken: String? = null,
		val name: String
): SsmCommand

@JsExport
@Serializable
@JsName("SsmGetUserResult")
class SsmGetUserResult(
	val user: SsmAgentBase?
): Event
