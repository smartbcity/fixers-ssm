package ssm.chaincode.dsl.query

import f2.dsl.cqrs.Event
import f2.dsl.fnc.F2Function
import kotlin.js.JsExport
import kotlin.js.JsName
import kotlinx.serialization.Serializable
import ssm.chaincode.dsl.SsmChaincodeConfig
import ssm.chaincode.dsl.SsmCommandDTO

/**
 * Retrieves a list of the existing sessions
 * @d2 function
 * @parent [ssm.chaincode.dsl.SsmSession]
 * @title List Sessions
 * @order 30
 */
typealias SsmListSessionQueryFunction = F2Function<SsmListSessionQuery, SsmListSessionResult>

/**
 * @d2 query
 * @parent [SsmListSessionQueryFunction]
 * @title List Sessions: Parameters
 */
@Serializable
@JsExport
@JsName("SsmListSessionQuery")
class SsmListSessionQuery(
	override val bearerToken: String? = null,
) : SsmCommandDTO

/**
 * @d2 event
 * @parent [SsmListSessionQueryFunction]
 * @title List Sessions: Result
 */
@Serializable
@JsExport
@JsName("SsmListSessionResult")
class SsmListSessionResult(
	/**
	 * Names of all known sessions
	 * @example ["49b6566c-5616-4ca2-a94d-e3038a05f28e", "7f3af888-cb6f-4cc4-9471-8b75cf771e2e"]
	 */
	val values: Array<String>,
) : Event
