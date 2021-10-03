package ssm.data.dsl.features.query

actual interface DataQueryDTO {
	actual val ssm: SsmName
	actual val bearerToken: String?
}
