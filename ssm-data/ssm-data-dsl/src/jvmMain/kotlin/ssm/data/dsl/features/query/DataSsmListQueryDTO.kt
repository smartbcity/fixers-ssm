package ssm.data.dsl.features.query

import ssm.data.dsl.model.DataSsmDTO

actual interface DataSsmListQueryDTO

actual interface DataSsmListQueryResultDTO {
	actual val items: List<DataSsmDTO>
}
