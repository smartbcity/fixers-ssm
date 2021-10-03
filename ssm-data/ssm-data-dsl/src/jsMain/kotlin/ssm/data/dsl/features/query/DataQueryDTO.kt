package ssm.data.dsl.features.query

@JsExport
@JsName("TxQueryDTO")
actual external interface DataQueryDTO {
	actual val ssm: SsmName
	actual val bearerToken: String?
}
