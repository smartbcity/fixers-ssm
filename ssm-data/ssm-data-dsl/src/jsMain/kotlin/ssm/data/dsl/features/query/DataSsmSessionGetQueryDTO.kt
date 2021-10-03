package ssm.data.dsl.features.query

import ssm.data.dsl.model.DataSsmSessionDTO

@JsExport
@JsName("DataSsmSessionGetQueryDTO")
actual external interface DataSsmSessionGetQueryDTO : DataQueryDTO {
	actual val sessionId: String
	actual override val ssm: SsmName
	actual override val bearerToken: String?
}

@JsExport
@JsName("DataSsmSessionGetQueryResultDTO")
actual external interface DataSsmSessionGetQueryResultDTO {
	actual val session: DataSsmSessionDTO?
}
