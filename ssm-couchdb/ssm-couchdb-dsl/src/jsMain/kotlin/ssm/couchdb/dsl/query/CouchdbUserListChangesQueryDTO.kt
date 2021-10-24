package ssm.couchdb.dsl.query

import f2.dsl.cqrs.Event
import f2.dsl.cqrs.Query
import ssm.chaincode.dsl.model.SsmAgent
import ssm.chaincode.dsl.model.SsmAgentDTO
import ssm.chaincode.dsl.model.uri.ChaincodeUri

@JsName("CouchdbUserListQueryDTO")
@JsExport()
actual external interface CouchdbUserListQueryDTO : Query {
	actual val chaincodeUri: ChaincodeUri
}

@JsName("CouchdbUserListQueryResultDTO")
@JsExport
actual external interface CouchdbUserListQueryResultDTO : Event {
	actual val items: List<SsmAgentDTO>
}
