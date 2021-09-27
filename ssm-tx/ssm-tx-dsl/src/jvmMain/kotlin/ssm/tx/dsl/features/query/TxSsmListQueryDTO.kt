package ssm.tx.dsl.features.query

import ssm.chaincode.dsl.model.ChaincodeId
import ssm.chaincode.dsl.model.ChannelId
import ssm.tx.dsl.model.TxSsmDTO

actual interface TxSsmListQueryDTO {
	actual val channelId: ChannelId
	actual val chaincodeId: ChaincodeId
}

actual interface TxSsmListQueryResultDTO {
	actual val list: List<TxSsmDTO>
}
