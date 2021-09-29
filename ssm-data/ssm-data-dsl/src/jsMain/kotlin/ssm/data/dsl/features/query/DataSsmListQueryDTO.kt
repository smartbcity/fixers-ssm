package ssm.data.dsl.features.query

import ssm.chaincode.dsl.model.ChaincodeId
import ssm.chaincode.dsl.model.ChannelId
import ssm.data.dsl.model.DataSsmDTO

@JsExport
@JsName("DataSsmListQueryDTO")
actual external interface DataSsmListQueryDTO {
	actual val channelId: ChannelId
	actual val chaincodeId: ChaincodeId
}

@JsExport
@JsName("DataSsmListQueryResultDTO")
actual external interface DataSsmListQueryResultDTO {
	actual val list: List<DataSsmDTO>
}
