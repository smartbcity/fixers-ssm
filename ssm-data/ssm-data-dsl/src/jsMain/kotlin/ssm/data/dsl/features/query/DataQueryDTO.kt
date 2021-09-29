package ssm.data.dsl.features.query

@JsExport
@JsName("TxQueryDTO")
actual external interface DataQueryDTO {
	actual val ssm: ssm.data.dsl.features.query.SsmName
	actual val bearerToken: String?
}
