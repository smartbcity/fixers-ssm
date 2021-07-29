package ssm.chaincode.dsl.query

import f2.dsl.cqrs.Event
import f2.dsl.fnc.F2Function
import kotlinx.serialization.Serializable
import ssm.chaincode.dsl.Ssm
import ssm.chaincode.dsl.SsmCommandDTO
import kotlin.js.JsExport
import kotlin.js.JsName

/**
 * Retrieves an SSM
 * @d2 function
 * @parent [Ssm]
 * @title Get SSM
 */
typealias SsmGetQueryFunction = F2Function<SsmGetQuery, SsmGetResult>

/**
 * @d2 query
 * @parent [SsmGetQueryFunction]
 * @title Get SSM: Parameters
 */
@JsExport
@Serializable
@JsName("SsmGetQuery")
class SsmGetQuery(
	override val baseUrl: String,
	override val channelId: String?,
	override val chaincodeId: String?,
	override val bearerToken: String? = null,

	/**
	 * Identifier of the SSM to retrieve
	 * @example [Ssm.name]
	 */
	val name: String
): SsmCommandDTO

/**
 * @d2 event
 * @parent [SsmGetQueryFunction]
 * @title Get SSM: Result
 */
@JsExport
@Serializable
@JsName("SsmGetResult")
class SsmGetResult(
	/**
	 * Retrieved SSM with the given name if it exists
	 */
	val ssm: Ssm?
): Event
