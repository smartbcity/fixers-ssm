package ssm.data.dsl.features.query

import ssm.chaincode.dsl.model.uri.SsmUriDTO

actual interface DataQueryDTO {
	actual val ssmUri: SsmUriDTO
}
