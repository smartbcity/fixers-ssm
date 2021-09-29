package ssm.data.dsl.features.query

import ssm.data.dsl.model.DataSsm

@JsExport
@JsName("DataSsmGetQueryDTO")
actual external interface DataSsmGetQueryDTO : ssm.data.dsl.features.query.DataQueryDTO {
	actual override val ssm: ssm.data.dsl.features.query.SsmName
	actual override val bearerToken: String?
}

@JsExport
@JsName("DataSsmGetQueryResultDTO")
actual external interface DataSsmGetQueryResultDTO {
	actual val ssm: DataSsm?
}
