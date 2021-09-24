package ssm.couchdb.dsl.query

import f2.dsl.cqrs.Event
import f2.dsl.cqrs.Query
import f2.dsl.cqrs.page.PageDTO
import f2.dsl.cqrs.page.PageQuery
import f2.dsl.cqrs.page.PageQueryDTO
import f2.dsl.cqrs.page.PageQueryResultDTO
import ssm.chaincode.dsl.ChaincodeId
import ssm.chaincode.dsl.ChannelId
import ssm.chaincode.dsl.Ssm
import ssm.chaincode.dsl.SsmDTO

@JsExport
@JsName("CouchdbSsmListQueryDTO")
actual external interface CouchdbSsmListQueryDTO : PageQueryDTO {
	actual val channelId: ChannelId
	actual val chaincodeId: ChaincodeId
}

@JsExport
@JsName("CouchdbSsmListQueryResultDTO")
actual external interface CouchdbSsmListQueryResultDTO : PageQueryResultDTO<SsmDTO> {
	actual override val page: PageDTO<SsmDTO>
}
