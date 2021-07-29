package ssm.tx.dsl.features.query

import ssm.tx.dsl.model.TxSsmDTO

@JsExport
@JsName("TxSsmListQueryDTO")
actual external interface TxSsmListQueryDTO {
}

@JsExport
@JsName("TxSsmListQueryResultDTO")
actual external interface TxSsmListQueryResultDTO {
	actual val list: List<TxSsmDTO>
}