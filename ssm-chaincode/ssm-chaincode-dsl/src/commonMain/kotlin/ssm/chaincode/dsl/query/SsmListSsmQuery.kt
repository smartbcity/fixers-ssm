package ssm.chaincode.dsl.query

import f2.dsl.cqrs.Event
import f2.dsl.fnc.F2Function
import kotlinx.serialization.Serializable
import ssm.chaincode.dsl.Ssm
import ssm.chaincode.dsl.SsmCommandDTO
import kotlin.js.JsExport
import kotlin.js.JsName

/**
 * Retrieves a list of the existing SSMs
 * @d2 function
 * @parent [Ssm]
 * @title Get list SSM
 */
typealias SsmListSsmQueryFunction = F2Function<SsmListSsmQuery, SsmListSsmResult>

/**
 * @d2 query
 * @parent [SsmListSsmQueryFunction]
 * @title Get list SSM: Parameters
 */
@Serializable
@JsExport
@JsName("SsmListSsmQuery")
class SsmListSsmQuery(
	override val baseUrl: String,
	override val channelId: String?,
	override val chaincodeId: String?,
	override val bearerToken: String? = null,
): SsmCommandDTO

/**
 * @d2 event
 * @parent [SsmListSsmQueryFunction]
 * @title Get list SSM: Result
 */
@Serializable
@JsExport
@JsName("SsmListSsmResult")
class SsmListSsmResult(
	/**
	 * Names of all known SSMs
	 * @example ["ProductLogistic", "Delivery", "Mobility"]
	 */
	val values: Array<String>
): Event
