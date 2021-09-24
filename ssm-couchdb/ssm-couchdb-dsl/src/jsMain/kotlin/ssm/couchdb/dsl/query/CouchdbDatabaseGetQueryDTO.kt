package ssm.couchdb.dsl.query

import f2.dsl.cqrs.Event
import f2.dsl.cqrs.Query
import f2.dsl.cqrs.page.PageQuery
import f2.dsl.cqrs.page.PageQueryDTO
import ssm.chaincode.dsl.ChaincodeId
import ssm.chaincode.dsl.ChannelId
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
