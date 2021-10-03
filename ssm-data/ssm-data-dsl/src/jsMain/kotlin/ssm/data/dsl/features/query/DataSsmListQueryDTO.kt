package ssm.data.dsl.features.query

import ssm.data.dsl.model.DataSsmDTO

@JsExport
@JsName("DataSsmListQueryDTO")
actual external interface DataSsmListQueryDTO

@JsExport
@JsName("DataSsmListQueryResultDTO")
actual external interface DataSsmListQueryResultDTO {
	actual val list: List<DataSsmDTO>
}
