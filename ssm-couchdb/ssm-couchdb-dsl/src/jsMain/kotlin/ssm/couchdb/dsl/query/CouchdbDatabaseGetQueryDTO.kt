package ssm.couchdb.dsl.query

import f2.dsl.cqrs.Event
import f2.dsl.cqrs.Query
import ssm.chaincode.dsl.model.ChaincodeId
import ssm.chaincode.dsl.model.ChannelId
import ssm.couchdb.dsl.model.DatabaseDTO

@JsExport
@JsName("CouchdbDatabaseGetQueryDTO")
actual external interface CouchdbDatabaseGetQueryDTO : Query {
	actual val channelId: ChannelId
	actual val chaincodeId: ChaincodeId
}

@JsExport
@JsName("CouchdbDatabaseGetQueryResultDTO")
actual external interface CouchdbDatabaseGetQueryResultDTO: Event {
	actual val item: DatabaseDTO?
}
