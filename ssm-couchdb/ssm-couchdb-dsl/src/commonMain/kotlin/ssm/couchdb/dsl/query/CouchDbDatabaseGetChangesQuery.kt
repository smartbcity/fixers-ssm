package ssm.couchdb.dsl.query

import f2.dsl.cqrs.Event
import f2.dsl.cqrs.Query
import f2.dsl.fnc.F2Function
import kotlin.js.JsExport
import kotlin.js.JsName
import kotlinx.serialization.Serializable
import ssm.chaincode.dsl.model.ChaincodeId
import ssm.chaincode.dsl.model.ChannelId
import ssm.couchdb.dsl.model.ChangeEventId
import ssm.couchdb.dsl.model.DatabaseChanges
import ssm.couchdb.dsl.model.DatabaseChangesDTO
import ssm.couchdb.dsl.model.DocType

/**
 * @title Retrieves all changes on a database
 * @d2 function
 * @order 20
 * @parent [ssm.couchdb.dsl.SsmCouchdbD2Query]
 */
typealias CouchDbDatabaseGetChangesQueryFunction = F2Function<CouchdbDatabaseGetChangesQueryDTO, CouchdbDatabaseGetChangesQueryResultDTO>

/**
 * @title Get Database: Parameters
 * @d2 model
 * @parent [CouchdbDatabaseGetQueryFunction]
 */
expect interface CouchdbDatabaseGetChangesQueryDTO : Query {
	/**
	 * The unique id of a channel.
	 */
	val channelId: ChannelId
	/**
	 * The unique id of a chaincode.
	 */
	val chaincodeId: ChaincodeId
	/**
	 * Filter result by doctype
	 */
	val docType: DocType<*>
	/**
	 * Filter to start the results from the the ID of the last events received by the server on a previous connection
	 */
	val lastEventId: ChangeEventId?
}

/**
 * @d2 model
 * @title Get Database: Result
 * @parent [CouchdbDatabaseGetQueryFunction]
 */
expect interface CouchdbDatabaseGetChangesQueryResultDTO : Event {
	/**
	 * The name of the database.
	 */
	val items: List<DatabaseChangesDTO>
}

@Serializable
@JsExport
@JsName("CouchdbDatabaseGetChangesQuery")
class CouchdbDatabaseGetChangesQuery(
	override val channelId: ChannelId,
	override val chaincodeId: ChaincodeId,
	override val docType: DocType<*>,
	override val lastEventId: ChangeEventId?,
) : CouchdbDatabaseGetChangesQueryDTO

@Serializable
@JsExport
@JsName("CouchdbSsmDatabaseGetChangesQueryResult")
class CouchdbSsmDatabaseGetChangesQueryResult(
	override val items: List<DatabaseChanges>,
) : CouchdbDatabaseGetChangesQueryResultDTO
