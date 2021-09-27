package ssm.couchdb.dsl.query

import f2.dsl.cqrs.page.PageDTO
import f2.dsl.cqrs.page.PageQueryDTO
import f2.dsl.cqrs.page.PageQueryResultDTO
import ssm.chaincode.dsl.model.ChaincodeId
import ssm.chaincode.dsl.model.ChannelId
import ssm.chaincode.dsl.model.SsmName
import ssm.chaincode.dsl.model.SsmSessionStateDTO

actual interface CouchdbSsmSessionStateListQueryDTO : PageQueryDTO {
	actual val channelId: ChannelId
	actual val chaincodeId: ChaincodeId
	actual val ssm: SsmName?
}

actual interface CouchdbSsmSessionStateListQueryResultDTO: PageQueryResultDTO<SsmSessionStateDTO> {
	/**
	 * Retrieved sessions
	 */
	actual override val page: PageDTO<SsmSessionStateDTO>
}
