package ssm.data.dsl.features.query

import ssm.chaincode.dsl.model.uri.SsmUri

actual interface DataQueryDTO {
	actual val ssm: SsmUri
}
