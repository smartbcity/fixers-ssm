package ssm.tx.dsl.features.query

import ssm.chaincode.dsl.ChaincodeId
import ssm.chaincode.dsl.ChannelId
import ssm.tx.dsl.model.TxSsmSessionDTO

actual interface TxSsmSessionListQueryDTO : TxQueryDTO {
	actual val channelId: ChannelId
	actual val chaincodeId: ChaincodeId
}

actual interface TxSsmSessionListQueryResultDTO {
	actual val list: List<TxSsmSessionDTO>
}
