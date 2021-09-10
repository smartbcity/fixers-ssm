package ssm.tx.dsl.features.query

import ssm.tx.dsl.model.TxSsmSessionDTO

@JsExport
@JsName("TxSsmSessionListQueryDTO")
actual external interface TxSsmSessionListQueryDTO : TxQueryDTO

@JsExport
@JsName("TxSsmSessionListQueryResultDTO")
actual external interface TxSsmSessionListQueryResultDTO {
	actual val list: List<TxSsmSessionDTO>
}
