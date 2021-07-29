package ssm.tx.dsl.features.query

import ssm.tx.dsl.model.TxSsmSessionDTO

actual interface TxSsmSessionListQueryDTO : TxQueryDTO {
	actual abstract override val ssm: String
}

actual interface TxSsmSessionListQueryResultDTO {
	actual val list: List<TxSsmSessionDTO>
}