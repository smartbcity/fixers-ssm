package ssm.tx.dsl.features.query

import ssm.tx.dsl.model.TxSsmSessionId
import ssm.tx.dsl.model.TxSsmSessionState

actual interface TxSsmSessionLogListQueryDTO : TxQueryDTO {
	actual val sessionId: TxSsmSessionId
	actual override val ssm: SsmName
	actual override val bearerToken: String?
}

actual interface TxSsmSessionLogListQueryResultDTO {
	actual val list: List<TxSsmSessionState>
}
