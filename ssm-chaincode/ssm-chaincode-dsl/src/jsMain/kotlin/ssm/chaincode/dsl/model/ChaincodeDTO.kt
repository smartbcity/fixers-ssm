package ssm.chaincode.dsl.model

import ssm.chaincode.dsl.model.ChaincodeId
import ssm.chaincode.dsl.model.ChannelId

@JsExport
@JsName("ChaincodeDTO")
actual external interface ChaincodeDTO {
	actual val id: ChaincodeId
	actual val channelId: ChannelId
}
