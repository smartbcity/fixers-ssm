package ssm.tx.dsl.features.query

import ssm.chaincode.dsl.ChaincodeId
import ssm.chaincode.dsl.ChannelId
import ssm.tx.dsl.model.TxSsmDTO

@JsExport
@JsName("TxSsmListQueryDTO")
actual external interface TxSsmListQueryDTO {
	actual val channelId: ChannelId
	actual val chaincodeId: ChaincodeId
}

@JsExport
@JsName("TxSsmListQueryResultDTO")
actual external interface TxSsmListQueryResultDTO {
	actual val list: List<TxSsmDTO>
}
