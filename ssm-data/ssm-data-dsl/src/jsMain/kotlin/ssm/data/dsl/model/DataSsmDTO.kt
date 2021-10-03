package ssm.data.dsl.model

import ssm.chaincode.dsl.model.SsmDTO

@JsExport
@JsName("DataSsmDTO")
actual external interface DataSsmDTO {
	actual val ssm: SsmDTO
	actual val channel: DataChannelDTO
	actual val version: SsmVersion?
}
