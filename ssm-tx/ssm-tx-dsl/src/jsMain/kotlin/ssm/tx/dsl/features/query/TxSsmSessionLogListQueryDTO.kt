package ssm.tx.dsl.features.query

import ssm.tx.dsl.model.TxSsmSessionId
import ssm.tx.dsl.model.TxSsmSessionState

@JsExport
@JsName("TxSsmSessionLogListQueryDTO")
actual external interface TxSsmSessionLogListQueryDTO : TxQueryDTO {
	actual val sessionId: TxSsmSessionId
	actual override val ssm: SsmName
	actual override val bearerToken: String?
}

@JsExport
@JsName("TxSsmSessionLogListQueryResultDTO")
actual external interface TxSsmSessionLogListQueryResultDTO {
	actual val list: List<TxSsmSessionState>
}
