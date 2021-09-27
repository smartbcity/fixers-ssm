package ssm.chaincode.dsl.query

import f2.dsl.cqrs.Event
import f2.dsl.fnc.F2Function
import kotlin.js.JsExport
import kotlin.js.JsName
import kotlinx.serialization.Serializable
import ssm.chaincode.dsl.Ssm
import ssm.chaincode.dsl.SsmChaincodeConfig
import ssm.chaincode.dsl.SsmCommandDTO

/**
 * Retrieves a list of the existing SSMs
 * @d2 function
 * @parent [Ssm]
 * @title List SSMs
 * @order 20
 */
typealias SsmListSsmQueryFunction = F2Function<SsmListSsmQuery, SsmListSsmResult>

/**
 * @d2 query
 * @parent [SsmListSsmQueryFunction]
 * @title List SSMs: Parameters
 */
@Serializable
@JsExport
@JsName("SsmListSsmQuery")
class SsmListSsmQuery(
	override val bearerToken: String? = null,
) : SsmCommandDTO

/**
 * @d2 event
 * @parent [SsmListSsmQueryFunction]
 * @title List SSMs: Result
 */
@Serializable
@JsExport
@JsName("SsmListSsmResult")
class SsmListSsmResult(
	/**
	 * Names of all known SSMs
	 * @example ["ProductLogistic", "Delivery", "Mobility"]
	 */
	val values: Array<String>,
) : Event
