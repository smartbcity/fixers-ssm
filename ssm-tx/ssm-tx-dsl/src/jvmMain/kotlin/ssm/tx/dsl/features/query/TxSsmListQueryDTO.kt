package ssm.tx.dsl.features.query

import ssm.tx.dsl.model.TxSsmDTO

actual interface TxSsmListQueryDTO {
}

actual interface TxSsmListQueryResultDTO {
	actual val list: List<TxSsmDTO>
}