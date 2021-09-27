package ssm.tx.dsl.features.query

import ssm.chaincode.dsl.model.ChaincodeId
import ssm.chaincode.dsl.model.ChannelId
import ssm.tx.dsl.model.TxSsmSessionDTO

@JsExport
@JsName("TxSsmSessionListQueryDTO")
actual external interface TxSsmSessionListQueryDTO : TxQueryDTO {
	actual val channelId: ChannelId
	actual val chaincodeId: ChaincodeId
}

@JsExport
@JsName("TxSsmSessionListQueryResultDTO")
actual external interface TxSsmSessionListQueryResultDTO {
	actual val list: List<TxSsmSessionDTO>
}
