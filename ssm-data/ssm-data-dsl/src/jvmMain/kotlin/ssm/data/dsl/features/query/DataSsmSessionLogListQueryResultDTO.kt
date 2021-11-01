package ssm.data.dsl.features.query

import ssm.chaincode.dsl.model.uri.SsmUri
import ssm.data.dsl.model.DataSsmSessionId
import ssm.data.dsl.model.DataSsmSessionState

actual interface DataSsmSessionLogListQueryDTO : DataQueryDTO {
	actual val sessionId: DataSsmSessionId
	actual override val ssm: SsmUri
}

actual interface DataSsmSessionLogListQueryResultDTO {
	actual val items: List<DataSsmSessionState>
}
