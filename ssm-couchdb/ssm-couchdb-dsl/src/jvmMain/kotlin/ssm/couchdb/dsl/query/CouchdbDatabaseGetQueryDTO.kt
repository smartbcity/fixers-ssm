package ssm.couchdb.dsl.query

import f2.dsl.cqrs.Event
import f2.dsl.cqrs.Query
import ssm.chaincode.dsl.model.ChaincodeId
import ssm.chaincode.dsl.model.ChannelId
import ssm.couchdb.dsl.model.DatabaseDTO

actual interface CouchdbDatabaseGetQueryDTO : Query {
	actual val channelId: ChannelId
	actual val chaincodeId: ChaincodeId
}

actual interface CouchdbDatabaseGetQueryResultDTO : Event {
	actual val item: DatabaseDTO?
}
