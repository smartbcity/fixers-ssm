package ssm.couchdb.dsl.query

import f2.dsl.cqrs.page.PageDTO
import f2.dsl.cqrs.page.PageQueryDTO
import f2.dsl.cqrs.page.PageQueryResultDTO
import ssm.chaincode.dsl.model.SsmName
import ssm.chaincode.dsl.model.SsmSessionStateDTO
import ssm.chaincode.dsl.model.uri.ChaincodeUriDTO

actual interface CouchdbSsmSessionStateListQueryDTO : PageQueryDTO {
	actual val chaincodeUri: ChaincodeUriDTO
	actual val ssm: SsmName?
}

actual interface CouchdbSsmSessionStateListQueryResultDTO: PageQueryResultDTO<SsmSessionStateDTO> {
	/**
	 * Retrieved sessions
	 */
	actual override val page: PageDTO<SsmSessionStateDTO>
}
