package ssm.chaincode.dsl.query

import f2.dsl.cqrs.Event
import f2.dsl.function.F2Function
import f2.dsl.function.F2FunctionRemote
import kotlinx.serialization.Serializable
import ssm.chaincode.dsl.SsmCommand
import kotlin.js.JsExport
import kotlin.js.JsName

typealias SsmListSessionQueryFunction = F2Function<SsmListSessionQuery, SsmListSessionResult>
typealias SsmListSessionRemoteQueryFunction = F2FunctionRemote<SsmListSessionQuery, SsmListSessionResult>

@JsExport
@Serializable
@JsName("SsmListSessionQuery")
class SsmListSessionQuery(
		override val baseUrl: String,
		override val channelId: String?,
		override val chaincodeId: String?,
		override val bearerToken: String? = null,
): SsmCommand


@JsExport
@Serializable
@JsName("SsmListSessionResult")
class SsmListSessionResult(
		val values: Array<String>
): Event
