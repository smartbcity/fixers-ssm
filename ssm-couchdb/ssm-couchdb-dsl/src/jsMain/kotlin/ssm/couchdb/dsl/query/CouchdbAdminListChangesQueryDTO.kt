package ssm.couchdb.dsl.query

import f2.dsl.cqrs.Event
import f2.dsl.cqrs.Query
import ssm.chaincode.dsl.model.SsmAgentDTO
import ssm.chaincode.dsl.model.uri.ChaincodeUri
import ssm.chaincode.dsl.model.uri.ChaincodeUriDTO

@JsName("CouchdbAdminListQueryDTO")
@JsExport()
actual external interface CouchdbAdminListQueryDTO : Query {
	/**
	 * The unique id of a chaincode.
	 */
	actual val chaincodeUri: ChaincodeUriDTO
}

@JsName("CouchdbAdminListQueryResultDTO")
@JsExport
actual external interface CouchdbAdminListQueryResultDTO : Event {
	actual val items: List<SsmAgentDTO>
}
