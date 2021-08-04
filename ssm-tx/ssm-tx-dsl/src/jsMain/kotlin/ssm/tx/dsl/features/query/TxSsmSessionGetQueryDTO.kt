package ssm.tx.dsl.features.query

import ssm.tx.dsl.model.TxSsmSessionDTO

@JsExport
@JsName("TxSsmSessionGetQueryDTO")
actual external interface TxSsmSessionGetQueryDTO: TxQueryDTO {
	actual val sessionId: String
	actual override val ssm: SsmName
	actual override val bearerToken: String?
}

@JsExport
@JsName("TxSsmSessionGetQueryResultDTO")
actual external interface TxSsmSessionGetQueryResultDTO {
	actual val session: TxSsmSessionDTO?
}