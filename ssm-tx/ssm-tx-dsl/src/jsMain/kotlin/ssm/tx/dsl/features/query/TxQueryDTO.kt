package ssm.tx.dsl.features.query

@JsExport
@JsName("TxQueryDTO")
actual external interface TxQueryDTO {
	actual val ssm: SsmName
	actual val bearerToken: String?
}
