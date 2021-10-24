package ssm.couchdb.dsl.query

import f2.dsl.cqrs.Event
import f2.dsl.cqrs.Query
import f2.dsl.fnc.F2Function
import kotlin.js.JsExport
import kotlin.js.JsName
import kotlinx.serialization.Serializable
import ssm.chaincode.dsl.model.SessionName
import ssm.chaincode.dsl.model.SsmSessionStateDTO
import ssm.chaincode.dsl.model.uri.SsmUri

/**
 * Retrieve the list of all known sessions of a given SSM
 * @d2 function
 * @parent [ssm.couchdb.dsl.SsmCouchdbD2Query]
 * @order 40
 * @title List Sessions
 */
typealias CouchdbSsmSessionStateGetQueryFunction = F2Function<CouchdbSsmSessionStateGetQueryDTO, CouchdbSsmSessionStateGetQueryResultDTO>

expect interface CouchdbSsmSessionStateGetQueryDTO : Query {
	val ssmUri: SsmUri
	val sessionName: SessionName
}

expect interface CouchdbSsmSessionStateGetQueryResultDTO : Event {
	/**
	 * Retrieved sessions
	 */
	val item: SsmSessionStateDTO
}

/**
 * @d2 query
 * @parent [CouchdbSsmSessionStateGetQueryFunction]
 * @title Get Sessions: Parameters
 */
@Serializable
@JsExport
@JsName("CouchdbSsmSessionStateGetQuery")
class CouchdbSsmSessionStateGetQuery(
	override val ssmUri: SsmUri, override val sessionName: SessionName
) : CouchdbSsmSessionStateGetQueryDTO

/**
 * @d2 event
 * @parent [CouchdbSsmSessionStateGetQueryFunction]
 * @title Get Sessions: Result
 */
@Serializable
@JsExport
@JsName("CouchdbSsmSessionStateGetQueryResult")
class CouchdbSsmSessionStateGetQueryResult(
	override val item: SsmSessionStateDTO
) : CouchdbSsmSessionStateGetQueryResultDTO
