package ssm.couchdb.dsl.query

import ssm.chaincode.dsl.SsmSessionStateBase
import ssm.couchdb.dsl.CdbQueryDTO

actual interface CdbSsmSessionListQueryDTO : CdbQueryDTO {
	actual val dbName: String
	actual val ssm: String?
	actual override val dbConfig: String
}

actual interface CdbSsmSessionListQueryResultDTO {
	actual val sessions: Array<SsmSessionStateBase>
}