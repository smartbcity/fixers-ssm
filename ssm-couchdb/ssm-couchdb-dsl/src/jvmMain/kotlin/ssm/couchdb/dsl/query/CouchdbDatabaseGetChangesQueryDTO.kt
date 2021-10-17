package ssm.couchdb.dsl.query

import f2.dsl.cqrs.Event
import f2.dsl.cqrs.Query
import ssm.chaincode.dsl.model.ChaincodeId
import ssm.chaincode.dsl.model.ChannelId
import ssm.couchdb.dsl.model.ChangeEventId
import ssm.couchdb.dsl.model.DatabaseChangesDTO
import ssm.couchdb.dsl.model.DatabaseDTO
import ssm.couchdb.dsl.model.DocType

actual interface CouchdbDatabaseGetChangesQueryDTO : Query {
	actual val channelId: ChannelId
	actual val chaincodeId: ChaincodeId
	actual val docType: DocType<*>
	actual val lastEventId: ChangeEventId?
}

actual interface CouchdbDatabaseGetChangesQueryResultDTO: Event {
	actual val items: List<DatabaseChangesDTO>
}
