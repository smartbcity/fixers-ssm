package ssm.couchdb.dsl.query

import ssm.chaincode.dsl.Ssm
import ssm.couchdb.dsl.CdbQueryDTO

actual interface CdbSsmListQueryDTO : CdbQueryDTO {
	actual val dbName: String
	actual override val dbConfig: String
}

actual interface CdbSsmListQueryResultDTO {
	actual val ssmList: List<Ssm>
}
