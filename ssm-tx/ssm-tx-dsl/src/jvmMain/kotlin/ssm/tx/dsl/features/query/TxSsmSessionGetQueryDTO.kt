package ssm.tx.dsl.features.query

import ssm.tx.dsl.model.TxSsmSessionDTO

actual interface TxSsmSessionGetQueryDTO: TxQueryDTO {
	actual val sessionId: String
	actual override val ssm: SsmName
	actual override val bearerToken: String?
}

actual interface TxSsmSessionQueryGetResultDTO {
	actual val session: TxSsmSessionDTO?
}