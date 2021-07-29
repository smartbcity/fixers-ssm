package ssm.tx.dsl.features.query

actual interface TxQueryDTO {
	actual val ssm: SsmName
	actual val bearerToken: String?
}
