package ssm.data.dsl.features.query

import ssm.chaincode.dsl.model.uri.SsmUri
import ssm.data.dsl.model.DataSsmSessionDTO

actual interface DataSsmSessionGetQueryDTO : DataQueryDTO {
	actual val sessionId: String
	actual override val ssm: SsmUri
	actual override val bearerToken: String?
}

actual interface DataSsmSessionGetQueryResultDTO {
	actual val session: DataSsmSessionDTO?
}
