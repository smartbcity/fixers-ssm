package ssm.data.dsl.features.query

actual interface DataQueryDTO {
	actual val ssm: ssm.data.dsl.features.query.SsmName
	actual val bearerToken: String?
}
