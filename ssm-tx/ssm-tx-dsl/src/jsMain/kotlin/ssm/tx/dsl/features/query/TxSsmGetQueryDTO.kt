package ssm.tx.dsl.features.query

import ssm.tx.dsl.model.TxSsm

@JsExport
@JsName("TxSsmGetQueryDTO")
actual external interface TxSsmGetQueryDTO : TxQueryDTO {
	actual override val ssm: SsmName
	actual override val bearerToken: String?
}

@JsExport
@JsName("TxSsmGetQueryResultDTO")
actual external interface TxSsmGetQueryResultDTO {
	actual val ssm: TxSsm?
}
