package ssm.couchdb.dsl.query

import f2.dsl.cqrs.Event
import f2.dsl.cqrs.Query
import f2.dsl.fnc.F2Function
import kotlin.js.JsExport
import kotlin.js.JsName
import kotlinx.serialization.Serializable
import ssm.chaincode.dsl.model.SsmAgent
import ssm.chaincode.dsl.model.SsmAgentDTO
import ssm.chaincode.dsl.model.uri.ChaincodeUri

/**
 * @title Fetch all admins
 * @d2 function
 * @order 20
 * @parent [ssm.couchdb.dsl.SsmCouchdbD2Query]
 */
typealias CouchdbUserListQueryFunction = F2Function<CouchdbUserListQueryDTO, CouchdbUserListQueryResultDTO>

/**
 * @title Get all chaincode: Parameters
 * @d2 model
 * @parent [CouchdbUserListQueryFunction]
 */
expect interface CouchdbUserListQueryDTO : Query {
	/**
	 * The unique id of a chaincode.
	 */
	val chaincodeUri: ChaincodeUri
}

/**
 * @d2 model
 * @title Get all admins: Result
 * @parent [CouchdbUserListQueryFunction]
 */
expect interface CouchdbUserListQueryResultDTO : Event {
	/**
	 * Names of the admin.
	 */
	val items: List<SsmAgentDTO>
}

@Serializable
@JsExport
@JsName("CouchdbUserListQuery")
class CouchdbUserListQuery(
	override val chaincodeUri: ChaincodeUri
) : CouchdbUserListQueryDTO

@Serializable
@JsExport
@JsName("CouchdbUserListQueryResult")
class CouchdbUserListQueryResult(
	override val items: List<SsmAgent>,
) : CouchdbUserListQueryResultDTO
