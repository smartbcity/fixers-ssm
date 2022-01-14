package ssm.data.dsl.features.query

import ssm.chaincode.dsl.model.SessionName
import ssm.chaincode.dsl.model.uri.SsmUri
import ssm.chaincode.dsl.model.uri.SsmUriDTO
import ssm.data.dsl.model.DataSsmSessionState

actual interface DataSsmSessionLogListQueryDTO : DataQueryDTO {
	actual val sessionName: SessionName
	actual override val ssmUri: SsmUriDTO
}

actual interface DataSsmSessionLogListQueryResultDTO {
	actual val items: List<DataSsmSessionState>
}
