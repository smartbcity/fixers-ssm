package ssm.data.dsl.features.query

import ssm.data.dsl.model.DataSsmSessionId
import ssm.data.dsl.model.DataSsmSessionState

actual interface DataSsmSessionLogListQueryDTO : ssm.data.dsl.features.query.DataQueryDTO {
	actual val sessionId: DataSsmSessionId
	actual override val ssm: ssm.data.dsl.features.query.SsmName
	actual override val bearerToken: String?
}

actual interface DataSsmSessionLogListQueryResultDTO {
	actual val list: List<DataSsmSessionState>
}
