package ssm.data.dsl.features.query

import ssm.data.dsl.model.DataSsm

actual interface DataSsmGetQueryDTO : ssm.data.dsl.features.query.DataQueryDTO {
	actual override val ssm: ssm.data.dsl.features.query.SsmName
	actual override val bearerToken: String?
}

actual interface DataSsmGetQueryResultDTO {
	actual val ssm: DataSsm?
}
