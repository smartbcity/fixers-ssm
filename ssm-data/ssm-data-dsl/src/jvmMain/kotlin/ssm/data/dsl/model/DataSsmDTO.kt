package ssm.data.dsl.model

import ssm.chaincode.dsl.model.SsmDTO
import ssm.chaincode.dsl.model.uri.SsmUri
import ssm.chaincode.dsl.model.uri.SsmVersion

actual interface DataSsmDTO {
	actual val uri: SsmUri
	actual val ssm: SsmDTO
	actual val channel: DataChannelDTO
	actual val version: SsmVersion?
}
