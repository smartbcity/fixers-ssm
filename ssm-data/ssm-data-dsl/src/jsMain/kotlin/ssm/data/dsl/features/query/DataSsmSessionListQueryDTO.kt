package ssm.data.dsl.features.query

import ssm.chaincode.dsl.model.ChaincodeId
import ssm.chaincode.dsl.model.ChannelId
import ssm.data.dsl.model.DataSsmSessionDTO

@JsExport
@JsName("DataSsmSessionListQueryDTO")
actual external interface DataSsmSessionListQueryDTO : ssm.data.dsl.features.query.DataQueryDTO {
	actual val channelId: ChannelId
	actual val chaincodeId: ChaincodeId
}

@JsExport
@JsName("DataSsmSessionListQueryResultDTO")
actual external interface DataSsmSessionListQueryResultDTO {
	actual val list: List<DataSsmSessionDTO>
}
