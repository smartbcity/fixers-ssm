package ssm.couchdb.dsl.query

import f2.dsl.cqrs.Event
import f2.dsl.cqrs.Query
import ssm.chaincode.dsl.model.uri.ChaincodeUri
import ssm.chaincode.dsl.model.uri.ChaincodeUriDTO

@JsName("CouchdbChaincodeListQueryDTO")
@JsExport()
actual external interface CouchdbChaincodeListQueryDTO : Query

@JsName("CouchdbChaincodeListQueryResultDTO")
@JsExport
actual external interface CouchdbChaincodeListQueryResultDTO : Event {
	actual val items: List<ChaincodeUriDTO>
}
