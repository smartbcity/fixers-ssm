package ssm.couchdb.dsl.query

import f2.dsl.cqrs.page.PageDTO
import f2.dsl.cqrs.page.PageQueryDTO
import f2.dsl.cqrs.page.PageQueryResultDTO
import ssm.chaincode.dsl.model.SsmName
import ssm.chaincode.dsl.model.SsmSessionStateDTO
import ssm.chaincode.dsl.model.uri.ChaincodeUri
import ssm.chaincode.dsl.model.uri.ChaincodeUriDTO

@JsExport
@JsName("CouchdbSsmSessionStateListQueryDTO")
actual external interface CouchdbSsmSessionStateListQueryDTO : PageQueryDTO {
	actual val chaincodeUri: ChaincodeUriDTO
	actual val ssm: SsmName?
}

@JsExport
@JsName("CouchdbSsmSessionStateListQueryResultDTO")
actual external interface CouchdbSsmSessionStateListQueryResultDTO : PageQueryResultDTO<SsmSessionStateDTO> {
	actual override val page: PageDTO<SsmSessionStateDTO>
}
