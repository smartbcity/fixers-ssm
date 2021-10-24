package ssm.data.dsl.features.query

import f2.dsl.cqrs.Event
import ssm.chaincode.dsl.model.uri.SsmUri
import ssm.data.dsl.model.DataSsmSessionDTO

actual interface DataSsmSessionGetQueryDTO : DataQueryDTO {
	actual val sessionName: String
	actual override val ssm: SsmUri
	actual override val bearerToken: String?
}

actual interface DataSsmSessionGetQueryResultDTO: Event {
	actual val item: DataSsmSessionDTO?
}
