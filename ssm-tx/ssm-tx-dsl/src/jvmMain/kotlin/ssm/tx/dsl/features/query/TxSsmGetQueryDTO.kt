package ssm.tx.dsl.features.query

import ssm.tx.dsl.model.TxSsm

actual interface TxSsmGetQueryDTO: TxQueryDTO {
	actual override val ssm: SsmName
	actual override val bearerToken: String?
}

actual interface TxSsmGetQueryResultDTO {
	actual val ssm: TxSsm?
}