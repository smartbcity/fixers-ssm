package ssm.data.dsl.features.query

import ssm.chaincode.dsl.model.uri.SsmUriDTO
import ssm.data.dsl.model.DataSsm

actual interface DataSsmGetQueryDTO : DataQueryDTO {
	actual override val ssmUri: SsmUriDTO
}

actual interface DataSsmGetQueryResultDTO {
	actual val item: DataSsm?
}
