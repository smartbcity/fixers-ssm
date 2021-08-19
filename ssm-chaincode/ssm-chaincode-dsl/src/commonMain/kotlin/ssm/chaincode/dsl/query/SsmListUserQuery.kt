package ssm.chaincode.dsl.query

import f2.dsl.cqrs.Event
import f2.dsl.fnc.F2Function
import kotlinx.serialization.Serializable
import ssm.chaincode.dsl.SsmChaincodeProperties
import ssm.chaincode.dsl.SsmCommandDTO
import kotlin.js.JsExport
import kotlin.js.JsName

typealias SsmListUserQueryFunction = F2Function<SsmListUserQuery, SsmListUserResult>

@Serializable
@JsExport
@JsName("SsmListUserQuery")
class SsmListUserQuery(
	override val chaincode: SsmChaincodeProperties,
	override val bearerToken: String? = null,
): SsmCommandDTO

@Serializable
@JsExport
@JsName("SsmListUserResult")
class SsmListUserResult(
	val values: Array<String>
): Event
