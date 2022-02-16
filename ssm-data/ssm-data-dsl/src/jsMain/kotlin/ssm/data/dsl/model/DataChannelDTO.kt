package ssm.data.dsl.model

import ssm.chaincode.dsl.model.ChannelId

@JsExport
@JsName("DataChannelDTO")
actual external interface DataChannelDTO {
	actual val id: ChannelId
}
