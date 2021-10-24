package ssm.couchdb.dsl.query

import f2.dsl.cqrs.Event
import f2.dsl.cqrs.Query
import f2.dsl.cqrs.page.PageDTO
import f2.dsl.cqrs.page.PageQueryDTO
import f2.dsl.cqrs.page.PageQueryResultDTO
import ssm.chaincode.dsl.model.SessionName
import ssm.chaincode.dsl.model.SsmSessionStateDTO
import ssm.chaincode.dsl.model.uri.SsmUri

@JsExport
@JsName("CouchdbSsmSessionStateGetQueryDTO")
actual external interface CouchdbSsmSessionStateGetQueryDTO : Query {
	actual val ssmUri: SsmUri
	actual val sessionName: SessionName
}

@JsExport
@JsName("CouchdbSsmSessionStateGetQueryResultDTO")
actual external interface CouchdbSsmSessionStateGetQueryResultDTO : Event {
	actual val item: SsmSessionStateDTO
}
