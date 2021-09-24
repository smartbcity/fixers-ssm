package ssm.couchdb.dsl.query

import f2.dsl.cqrs.Event
import f2.dsl.cqrs.Query
import f2.dsl.cqrs.page.PageQueryDTO
import f2.dsl.cqrs.page.PageQueryResult
import f2.dsl.cqrs.page.PageQueryResultDTO
import ssm.chaincode.dsl.ChaincodeId
import ssm.chaincode.dsl.ChannelId
import ssm.couchdb.dsl.model.DatabaseDTO

@JsExport
@JsName("CouchdbDatabaseListQueryDTO")
actual external interface CouchdbDatabaseListQueryDTO : PageQueryDTO {
	actual val channelId: ChannelId?
	actual val chaincodeId: ChaincodeId?
}

@JsExport
@JsName("CouchdbDatabaseListQueryResultDTO")
actual external interface CouchdbDatabaseListQueryResultDTO : PageQueryResultDTO<DatabaseDTO>
