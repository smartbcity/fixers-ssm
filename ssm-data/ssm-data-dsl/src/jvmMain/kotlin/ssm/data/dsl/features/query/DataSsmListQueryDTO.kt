package ssm.data.dsl.features.query

import ssm.chaincode.dsl.model.ChaincodeId
import ssm.chaincode.dsl.model.ChannelId
import ssm.data.dsl.model.DataSsmDTO

actual interface DataSsmListQueryDTO {
	actual val channelId: ChannelId
	actual val chaincodeId: ChaincodeId
}

actual interface DataSsmListQueryResultDTO {
	actual val list: List<DataSsmDTO>
}
