package ssm.chaincode.dsl.model

@JsExport
@JsName("ChaincodeDTO")
actual external interface ChaincodeDTO {
	actual val id: ChaincodeId
	actual val channelId: ChannelId
}
