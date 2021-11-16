package ssm.couchdb.dsl.query

import f2.dsl.cqrs.Event
import f2.dsl.cqrs.Query
import f2.dsl.fnc.F2Function
import kotlin.js.JsExport
import kotlin.js.JsName
import kotlinx.serialization.Serializable
import ssm.chaincode.dsl.model.uri.ChaincodeUri

/**
 * @title Fetch all chaincodes
 * @d2 function
 * @order 20
 * @parent [ssm.couchdb.dsl.CouchdbSsmD2Query]
 */
typealias CouchdbChaincodeListQueryFunction = F2Function<CouchdbChaincodeListQueryDTO, CouchdbChaincodeListQueryResultDTO>

/**
 * @title Get all chaincode: Parameters
 * @d2 model
 * @parent [CouchdbChaincodeListQueryFunction]
 */
expect interface CouchdbChaincodeListQueryDTO : Query

/**
 * @d2 model
 * @title Get all chaincodes: Result
 * @parent [CouchdbChaincodeListQueryFunction]
 */
expect interface CouchdbChaincodeListQueryResultDTO : Event {
	/**
	 * The name of the database.
	 */
	val items: List<ChaincodeUri>
}

@Serializable
@JsExport
@JsName("CouchdbChaincodeListQuery")
class CouchdbChaincodeListQuery: CouchdbChaincodeListQueryDTO

@Serializable
@JsExport
@JsName("CouchdbChaincodeListQueryResult")
class CouchdbChaincodeListQueryResult(
	override val items: List<ChaincodeUri>,
) : CouchdbChaincodeListQueryResultDTO
