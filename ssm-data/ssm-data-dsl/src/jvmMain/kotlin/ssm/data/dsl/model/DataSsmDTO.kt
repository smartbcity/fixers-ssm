package ssm.data.dsl.model

import ssm.chaincode.dsl.model.SsmDTO

actual interface DataSsmDTO {
	actual val ssm: SsmDTO
	actual val channel: DataChannelDTO
	actual val version: SsmVersion
}
