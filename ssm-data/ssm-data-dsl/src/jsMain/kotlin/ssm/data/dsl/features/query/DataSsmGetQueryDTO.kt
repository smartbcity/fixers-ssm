package ssm.data.dsl.features.query

import ssm.data.dsl.model.DataSsm

@JsExport
@JsName("DataSsmGetQueryDTO")
actual external interface DataSsmGetQueryDTO : DataQueryDTO {
	actual override val ssm: SsmName
	actual override val bearerToken: String?
}

@JsExport
@JsName("DataSsmGetQueryResultDTO")
actual external interface DataSsmGetQueryResultDTO {
	actual val ssm: DataSsm?
}
