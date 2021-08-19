package ssm.chaincode.dsl.query

import f2.dsl.cqrs.Event
import f2.dsl.fnc.F2Function
import kotlinx.serialization.Serializable
import ssm.chaincode.dsl.SsmChaincodeProperties
import ssm.chaincode.dsl.SsmCommandDTO
import ssm.chaincode.dsl.SsmSessionState
import kotlin.js.JsExport
import kotlin.js.JsName

/**
 * Retrieves the current state of a session
 * @d2 function
 * @parent [ssm.chaincode.dsl.SsmSession]
 * @title Get Session
 * @order 10
 */
typealias SsmGetSessionQueryFunction = F2Function<SsmGetSessionQuery, SsmGetSessionResult>

/**
 * @d2 query
 * @parent [SsmGetSessionQueryFunction]
 * @title Get Session: Parameters
 */
@Serializable
@JsExport
@JsName("SsmGetSessionQuery")
class SsmGetSessionQuery(
	override val chaincode: SsmChaincodeProperties,
	override val bearerToken: String? = null,

	/**
	 * Identifier of the session to retrieve
	 * @example [SsmSessionState.session]
	 */
	val name: String
): SsmCommandDTO

/**
 * @d2 event
 * @parent [SsmGetSessionQueryFunction]
 * @title Get Session: Result
 */
@Serializable
@JsExport
@JsName("SsmGetSessionResult")
class SsmGetSessionResult(
	/**
	 * The current state of the session if it exists
	 */
	val session: SsmSessionState?
): Event
