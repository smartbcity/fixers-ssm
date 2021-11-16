package ssm.data.dsl.features.query

import f2.dsl.cqrs.Event
import ssm.chaincode.dsl.model.uri.SsmUri
import ssm.data.dsl.model.DataSsmSessionDTO

@JsExport
@JsName("DataSsmSessionGetQueryDTO")
actual external interface DataSsmSessionGetQueryDTO : DataQueryDTO {
	actual val sessionName: String
	actual override val ssm: SsmUri
}

@JsExport
@JsName("DataSsmSessionGetQueryResultDTO")
actual external interface DataSsmSessionGetQueryResultDTO : Event {
	actual val item: DataSsmSessionDTO?
}
