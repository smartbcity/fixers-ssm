package ssm.data.dsl.features.query

import ssm.chaincode.dsl.model.uri.SsmUri
import ssm.chaincode.dsl.model.uri.SsmUriDTO

@JsExport
@JsName("DataQueryDTO")
actual external interface DataQueryDTO {
	actual val ssmUri: SsmUriDTO
}
