package ssm.couchdb.dsl.query

import ssm.chaincode.dsl.SsmSessionStateBase
import ssm.couchdb.dsl.CdbQueryDTO

@JsExport
@JsName("CdbSsmSessionListQueryDTO")
actual external interface CdbSsmSessionListQueryDTO : CdbQueryDTO {
	actual val dbName: String
	actual val ssm: String?
	actual abstract override val dbConfig: String
}

@JsExport
@JsName("CdbSsmSessionListQueryResultDTO")
actual external interface CdbSsmSessionListQueryResultDTO {
	actual val sessions: Array<SsmSessionStateBase>
}