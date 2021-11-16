package ssm.couchdb.dsl.query

import f2.dsl.cqrs.page.PageQueryDTO
import f2.dsl.cqrs.page.PageQueryResultDTO
import ssm.chaincode.dsl.model.ChaincodeId
import ssm.chaincode.dsl.model.ChannelId
import ssm.couchdb.dsl.model.DatabaseDTO

actual interface CouchdbDatabaseListQueryDTO : PageQueryDTO {
	actual val channelId: ChannelId?
	actual val chaincodeId: ChaincodeId?
}
actual interface CouchdbDatabaseListQueryResultDTO :PageQueryResultDTO<DatabaseDTO>
