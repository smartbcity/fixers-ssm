package ssm.data.dsl.features.query

import ssm.data.dsl.model.DataSsmSessionDTO

actual interface DataSsmSessionListQueryDTO : DataQueryDTO

actual interface DataSsmSessionListQueryResultDTO {
	actual val list: List<DataSsmSessionDTO>
}
