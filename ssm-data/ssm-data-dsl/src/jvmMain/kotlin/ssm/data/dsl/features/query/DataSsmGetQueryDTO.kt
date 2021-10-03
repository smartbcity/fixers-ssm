package ssm.data.dsl.features.query

import ssm.data.dsl.model.DataSsm

actual interface DataSsmGetQueryDTO : DataQueryDTO {
	actual override val ssm: SsmName
	actual override val bearerToken: String?
}

actual interface DataSsmGetQueryResultDTO {
	actual val ssm: DataSsm?
}
