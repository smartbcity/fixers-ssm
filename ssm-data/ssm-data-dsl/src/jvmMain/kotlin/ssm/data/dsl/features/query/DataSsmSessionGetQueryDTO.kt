package ssm.data.dsl.features.query

import ssm.data.dsl.model.DataSsmSessionDTO

actual interface DataSsmSessionGetQueryDTO : DataQueryDTO {
	actual val sessionId: String
	actual override val ssm: SsmName
	actual override val bearerToken: String?
}

actual interface DataSsmSessionGetQueryResultDTO {
	actual val session: DataSsmSessionDTO?
}
