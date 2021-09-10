package ssm.couchdb.dsl.query

import ssm.chaincode.dsl.Ssm
import ssm.couchdb.dsl.CdbQueryDTO

@JsExport
@JsName("CdbSsmListQueryDTO")
actual external interface CdbSsmListQueryDTO : CdbQueryDTO {
	actual val dbName: String
	actual override val dbConfig: String
}

@JsExport
@JsName("CdbSsmListQueryResultDTO")
actual external interface CdbSsmListQueryResultDTO {
	actual val ssmList: List<Ssm>
}
