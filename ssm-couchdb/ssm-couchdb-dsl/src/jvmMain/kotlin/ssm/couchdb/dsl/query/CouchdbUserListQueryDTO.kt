package ssm.couchdb.dsl.query

import f2.dsl.cqrs.Event
import f2.dsl.cqrs.Query
import ssm.chaincode.dsl.model.SsmAgentDTO
import ssm.chaincode.dsl.model.uri.ChaincodeUriDTO

actual interface CouchdbUserListQueryDTO : Query {
	actual val chaincodeUri: ChaincodeUriDTO
}

actual interface CouchdbUserListQueryResultDTO : Event {
	actual val items: List<SsmAgentDTO>
}
