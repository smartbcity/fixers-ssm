package ssm.data.dsl.features.query

import ssm.chaincode.dsl.model.uri.SsmUri

@JsExport
@JsName("TxQueryDTO")
actual external interface DataQueryDTO {
	actual val ssm: SsmUri
	actual val bearerToken: String?
}
