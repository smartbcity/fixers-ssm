package ssm.chaincode.dsl.query

import f2.dsl.cqrs.Event
import f2.dsl.fnc.F2Function
import kotlin.js.JsExport
import kotlin.js.JsName
import kotlinx.serialization.Serializable
import ssm.chaincode.dsl.SsmQueryDTO
import ssm.chaincode.dsl.model.SessionName
import ssm.chaincode.dsl.model.SsmSessionState

/**
 * Retrieves the current state of a session
 * @d2 function
 * @parent [ssm.chaincode.dsl.model.SsmSession]
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
	override val bearerToken: String? = null,

	/**
	 * Identifier of the session to retrieve
	 * @example [SsmSessionState.session]
	 */
	val sessionName: SessionName,
) : SsmQueryDTO

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
	val session: SsmSessionState?,
) : Event
