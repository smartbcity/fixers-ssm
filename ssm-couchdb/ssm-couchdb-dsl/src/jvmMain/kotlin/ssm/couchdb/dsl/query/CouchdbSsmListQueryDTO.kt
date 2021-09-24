package ssm.couchdb.dsl.query

import f2.dsl.cqrs.page.PageDTO
import f2.dsl.cqrs.page.PageQueryDTO
import f2.dsl.cqrs.page.PageQueryResultDTO
import ssm.chaincode.dsl.ChaincodeId
import ssm.chaincode.dsl.ChannelId
import ssm.chaincode.dsl.SsmDTO

actual interface CouchdbSsmListQueryDTO : PageQueryDTO {
	actual val channelId: ChannelId
	actual val chaincodeId: ChaincodeId
}

actual interface CouchdbSsmListQueryResultDTO : PageQueryResultDTO<SsmDTO> {
	actual override val page: PageDTO<SsmDTO>
}
